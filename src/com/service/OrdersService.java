package com.service;

import com.config.ExceptionConfig;
import com.dao.OrdersDao;
import com.entity.Carts;
import com.entity.Items;
import com.entity.Orders;
import com.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName OrdersService
 * @Description TODO 订单服务类
 * @Author GuiGhost
 * @Date 2020/12/10 23:57
 * @Version 1.0
 **/
@Service
public class OrdersService {
    @Autowired
    private OrdersDao ordersDao;
    @Autowired
    private ItemsService itemsService;
    @Autowired
    private CartService cartService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private UserService userService;


    /**
     * @Author GuiGhost
     * @Description //TODO 修改订单（支付成功）业务
     * @Date 23:05 2020/12/12
     * @Param [orders]
     * @return boolean
     **/
    public boolean updateByPayed(Orders orders){
        return ordersDao.updateByPayed(orders);
    }


    /**
     * @Author GuiGhost
     * @Description //TODO 根据订单编号查询业务
     * @Date 16:40 2020/12/11
     * @Param [id]
     * @return com.entity.Orders
     **/
    public Orders getById(int id){
        return packOrders(ordersDao.selectById(id));
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 购物车结算业务
     * @Date 16:29 2020/12/11
     * @Param [userId]
     * @return int
     **/
    @Transactional
    public int save(int userId) throws ExceptionConfig.MyException {
        List<Carts> cartsList = cartService.getList(userId);
        if (Objects.isNull(cartsList) || cartsList.isEmpty()){
            throw new ExceptionConfig.MyException("购物车中没有商品");
        }

        //验证库存
        for (Carts carts : cartsList) {
            if (carts.getGood().getStock() < carts.getAmount()){//验证库存
                throw new ExceptionConfig.MyException("商品" + carts.getGood().getName() + "库存不足");
            }
            goodsService.updateStock(carts.getGood().getId(),carts.getAmount());//减少库存
            goodsService.updateSales(carts.getGood().getId(),carts.getAmount());//增加销量
        }

        int total = 0;//订单总价
        for (Carts carts : cartsList) {
            total += carts.getGood().getPrice() * carts.getAmount();
        }
        Users user = userService.getById(userId);
        Orders order = new Orders();
        //`total`, `amount`, `status`, `paytype`, `name`, `phone`, `address`, `systime`, `user_id`

        order.setTotal(total);
        order.setStatus(Orders.STATUS_NOPAY);
        order.setUserId(userId);
        order.setAmount(cartsList.size());
        order.setSystime(new Date());
        ordersDao.insertStatusByNopay(order);
        Integer orderId = order.getId();
        for (Carts carts : cartsList) {
            Items items = new Items();
            items.setOrderId(orderId);
            items.setAmount(carts.getAmount());
            items.setGoodId(carts.getGoodId());
            items.setPrice(carts.getGood().getPrice());
            itemsService.insert(items);
        }

        //清空购物车
        cartService.clean(userId);
        return orderId;
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 查询指定用的所有订单业务
     * @Date 00:12 2020/12/11
     * @Param [userId]
     * @return java.util.List<com.entity.Orders>
     **/
    public List<Orders> getListByUserId(int userId,int page,int size){
        return packOrders(ordersDao.selectListByUserId(userId,size*(page-1),size));
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 查询指定用户订单量业务
     * @Date 09:28 2020/12/14
     * @Param [userId]
     * @return int
     **/
    public long getCountByUserId(int userId){
        return ordersDao.selectCountByUserId(userId);
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 给集合中的订单类中的ItemsList属性赋值
    * @Date 00:09 2020/12/11
     * @Param [ordersList]
     * @return java.util.List<com.entity.Orders>
     **/
    public List<Orders> packOrders(List<Orders> ordersList){
        for (Orders orders : ordersList) {
            orders = packOrders(orders);
        }
        return ordersList;
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 给订单类中的ItemsList属性赋值
     * @Date 00:05 2020/12/11
     * @Param [orders]
     * @return com.entity.Orders
     **/
    public Orders packOrders(Orders orders){
        if (orders != null){
            orders.setItemList(itemsService.getListByOrderId(orders.getId()));
            orders.setUser(userService.getById(orders.getUserId()));
        }
        return orders;
    }


    /**
     * @Author GuiGhost
     * @Description //TODO 查询所有订单
     * @Date 10:11 2020/12/14
     * @Param []
     * @return java.util.List<com.entity.Orders>
     **/
    public List<Orders> getList(int page,int size){
        return packOrders(ordersDao.selectList(size * (page-1),size));
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 查询所有订单记录总数
     * @Date 10:30 2020/12/14
     * @Param []
     * @return long
     **/
    public long getCount(){
        return ordersDao.selectCount();
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 根据订单状态获取指定记录
     * @Date 10:33 2020/12/14
     * @Param [status]
     * @return java.util.List<com.entity.Orders>
     **/
    public List<Orders> getListByStatus(int status,int page,int size){
        return packOrders(ordersDao.selectListByStatus(status,size * (page-1),size));
    }

    
    /**
     * @Author GuiGhost
     * @Description //TODO 查询指定状态的订单记录数
     * @Date 10:35 2020/12/14
     * @Param [status]
     * @return long
     **/
    public long getCountByStatus(int status){
        return ordersDao.selectCountByStatus(status);
    }
}
