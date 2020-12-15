package com.service;

import com.dao.CartsDao;
import com.entity.Carts;
import com.entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @ClassName CartService
 * @Description TODO 购物车服务类
 * @Author GuiGhost
 * @Date 2020/12/07 14:47
 * @Version 1.0
 **/
@Service
public class CartService {

    @Autowired
    private CartsDao cartsDao;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private TypesService typesService;


    /**
     * @Author GuiGhost
     * @Description //TODO 获取购物车总数
     * @Date 16:04 2020/12/07
     * @Param [userId]
     * @return int
     **/
    public int getCount(int userId){
        return cartsDao.selectSumAmountByUserId(userId);
    }


    /**
     * @Author GuiGhost
     * @Description //TODO 查询购物车
     * @Date 21:38 2020/12/09
     * @Param [userId]
     * @return java.util.List<com.entity.Carts>
     **/
    public List<Carts> getList(int userId){
        return packCart(cartsDao.select(userId));
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 给List<Carts>中的每个Carts赋Goods的值
     * @Date 21:36 2020/12/09
     * @Param [cartsList]
     * @return java.util.List<com.entity.Carts>
     **/
    public List<Carts> packCart(List<Carts> cartsList){
        for (Carts cart : cartsList) {
            cart = packCart(cart);
        }
        return cartsList;
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 根据购物车中的商品id给购物车中的商品赋值
     * @Date 21:35 2020/12/09
     * @Param [carts]
     * @return com.entity.Carts
     **/
    public Carts packCart(Carts carts){
        if (carts != null){
            carts.setGood(goodsService.packGood(goodsService.getById(carts.getGoodId())));//根据商品编号找到对应商品
            carts.setTotal(carts.getAmount() * carts.getGood().getPrice());
        }
        return carts;
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 加入购物车业务
     * @Date 23:27 2020/12/09
     * @Param [goodId, userId]
     * @return boolean
     **/
    public boolean save(int goodId,int userId){
        Carts carts = cartsDao.selectCartByUserIdAndGoodId(userId, goodId);//查询购物车中记录
        if (Objects.nonNull(carts)){//如果记录存在，amount+1
            return cartsDao.updateAddAmount(carts.getId(),1);
        }
        carts = new Carts();
        carts.setGoodId(goodId);
        carts.setUserId(userId);
        carts.setAmount(1);//新加入的商品，数量默认为1
        return cartsDao.insert(carts);
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 增加购物车商品数量
     * @Date 01:10 2020/12/10
     * @Param [id]
     * @return boolean
     **/
    public boolean addAmount(int id){
        return cartsDao.updateAddAmount(id,1);
    }
    
    /**
     * @Author GuiGhost
     * @Description //TODO 减少购物车商品数量
     * @Date 01:12 2020/12/10
     * @Param [id]
     * @return boolean
     **/
    public boolean lessAmount(int id){
        Carts carts = cartsDao.selectById(id);
        if (carts.getAmount()-1<=0){
            return delete(id);
        }else {
            return cartsDao.updateLessAmount(id,1);
        }
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 删除指定购物车商品
     * @Date 01:29 2020/12/10
     * @Param [id]
     * @return boolean
     **/
    public boolean delete(int id){
        return cartsDao.delete(id);
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 清空指定用户购物车业务
     * @Date 16:28 2020/12/11
     * @Param [userId]
     * @return boolean
     **/
    public boolean clean(int userId){
        return cartsDao.clean(userId);
    }
}
