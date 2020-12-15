package com.dao;

import com.entity.Items;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import java.util.List;

/**
 * @InterfaceName ItemsDao
 * @Description TODO  订单项dao类
 * @Author GuiGhost
 * @Date 2020/12/10 22:58
 * @Version 1.0
 **/
public interface ItemsDao {

    /**
     * @Author GuiGhost
     * @Description //TODO 根据订单编号查询订单项
     * @Date 00:15 2020/12/11
     * @Param [orderId]
     * @return java.util.List<com.entity.Items>
     **/
    @Select("SELECT * FROM items WHERE order_id = #{orderId}")
    public List<Items> selectListByOrderId(@Param("orderId")int orderId);


    /**
     * @Author GuiGhost
     * @Description //TODO 添加订单项
     * @Date 11:03 2020/12/11
     * @Param [items]
     * @return boolean
     **/
    @Insert("INSERT INTO `emall`.`items`(`price`, `amount`, `order_id`, `good_id`) " +
            "VALUES (#{price}, #{amount}, #{orderId}, #{goodId})")
    @SelectKey(keyProperty = "id",statement = "SELECT LAST_INSERT_ID()",
    before = false,resultType = Integer.class)
    public boolean insert(Items items);
}
