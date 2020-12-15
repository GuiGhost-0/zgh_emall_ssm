package com.service;

import com.dao.ItemsDao;
import com.entity.Items;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ItemsService
 * @Description TODO  订单项服务类
 * @Author GuiGhost
 * @Date 2020/12/10 23:05
 * @Version 1.0
 **/
@Service
public class ItemsService {

    @Autowired
    private ItemsDao itemsDao;
    @Autowired
    private GoodsService goodsService;


    /**
     * @Author GuiGhost
     * @Description //TODO 添加订单项数据
     * @Date 16:22 2020/12/11
     * @Param [items]
     * @return boolean
     **/
    public boolean insert(Items items){
        return itemsDao.insert(items);
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 根据订单编号查询订单项业务
     * @Date 23:30 2020/12/10
     * @Param [orderId]
     * @return java.util.List<com.entity.Items>
     **/
    public List<Items> getListByOrderId(int orderId){
        return packItems(itemsDao.selectListByOrderId(orderId));
    }


    /**
     * @Author GuiGhost
     * @Description //TODO 封装多个订单项
     * @Date 23:28 2020/12/10
     * @Param [itemsList]
     * @return java.util.List<com.entity.Items>
     **/
    public List<Items> packItems(List<Items> itemsList){
        for (Items items : itemsList) {
            items = packItems(items);
        }
        return itemsList;
    }
    /**
     * @Author GuiGhost
     * @Description //TODO Items注入Goods数据
     * @Date 23:26 2020/12/10
     * @Param [items]
     * @return com.entity.Items
     **/
    private Items packItems(Items items){
        if (items != null){
            items.setGood(goodsService.packGood(goodsService.getById(items.getGoodId())));
        }
        return items;
    }
}
