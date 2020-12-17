package com.dao;

import com.entity.Orders;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @InterfaceName OrdersDao
 * @Description TODO 订单实体类
 * @Author GuiGhost
 * @Date 2020/12/10 23:34
 * @Version 1.0
 **/
public interface OrdersDao {

    /**
     * @Author GuiGhost
     * @Description //TODO 查询指定用户的订单量
     * @Date 09:27 2020/12/14
     * @Param [userId]
     * @return int
     **/
    @Select("SELECT COUNT(*) FROM orders WHERE user_id=#{userId}")
    public long selectCountByUserId(@Param("userId")int userId);

    /**
     * @Author GuiGhost
     * @Description //TODO 根据订单编号查询
     * @Date 16:40 2020/12/11
     * @Param [id]
     * @return com.entity.Orders
     **/
    @Select("SELECT * FROM orders WHERE id = #{id}")
    public Orders selectById(@Param("id") int id);

    /**
     * @Author GuiGhost
     * @Description //TODO 查询所有订单
     * @Date 23:43 2020/12/10
     * @Param []
     * @return java.util.List<com.entity.Orders>
     **/
    @Select("SELECT * FROM orders ORDER BY id DESC LIMIT #{begin},#{size}")
    public List<Orders> selectList(@Param("begin")int begin,@Param("size")int size);
    
    /**
     * @Author GuiGhost
     * @Description //TODO 查询所有订单量
     * @Date 10:13 2020/12/14
     * @Param []
     * @return long
     **/
    @Select("SELECT COUNT(*) FROM orders")
    public long selectCount();

    /**
     * @Author GuiGhost
     * @Description //TODO 查询指定用户的所有订单
     * @Date 23:42 2020/12/10
     * @Param [userId]
     * @return java.util.List<com.entity.Orders>
     **/
    @Select("SELECT * FROM orders WHERE user_id = #{userId} ORDER BY id DESC LIMIT #{begin},#{size}")
    public List<Orders> selectListByUserId(@Param("userId")int userId,@Param("begin")int begin,@Param("size")int size);

    /**
     * @Author GuiGhost
     * @Description //TODO 查询指定用户指定状态的订单
     * @Date 23:46 2020/12/10
     * @Param [userId, status]
     * @return java.util.List<com.entity.Orders>
     **/
    @Select("SELECT * FROM orders WHERE user_id = #{userId} AND status = #{status} ORDER BY id DESC")
    public List<Orders> selectListByUserIdAndStatus(@Param("userId")int userId,@Param("status")int status);

    /**
     * @Author GuiGhost
     * @Description //TODO 添加订单（未付款）
     * @Date 10:45 2020/12/11
     * @Param [orders]
     * @return boolean
     **/
    @Insert("INSERT INTO `emall`.`orders`(`total`, `amount`, `status`, `paytype`, `name`, `phone`, `address`, `systime`, `user_id`) " +
            "VALUES (#{total}, #{amount}, #{status}, 0, #{name}, #{phone}, #{address}, NOW(), #{userId})")
    @SelectKey(keyProperty = "id",statement = "SELECT LAST_INSERT_ID()",
    before = false,resultType = Integer.class)
    public boolean insertStatusByNopay(Orders orders);


    /**
     * @Author GuiGhost
     * @Description //TODO 修改订单状态（支付成功）
     * @Date 22:47 2020/12/12
     * @Param [orders]
     * @return boolean
     **/
    @Update("UPDATE orders SET " +
            "status = #{status},paytype=#{paytype},name=#{name},phone=#{phone},address=#{address},systime=NOW() " +
            "WHERE id=#{id}")
    public boolean updateByPayed(Orders orders);


    /**
     * @Author GuiGhost
     * @Description //TODO 根据订单状态查询订单记录
     * @Date 10:29 2020/12/14
     * @Param [status]
     * @return java.util.List<com.entity.Orders>
     **/
    @Select("SELECT * FROM orders WHERE status=#{status} ORDER BY id DESC LIMIT #{begin},#{size}")
    public List<Orders> selectListByStatus(@Param("status")int status,@Param("begin")int begin,@Param("size")int size);

    /**
     * @Author GuiGhost
     * @Description //TODO 根据订单状态查询订单记录数
     * @Date 10:30 2020/12/14
     * @Param [status]
     * @return long
     **/
    @Select("SELECT COUNT(*) FROM orders WHERE status=#{status}")
    public long selectCountByStatus(@Param("status")int status);


    /**
     * @Author GuiGhost
     * @Description //TODO 更新订单状态
     * @Date 16:33 2020/12/17
     * @Param [id, status]
     * @return boolean
     **/
    @Update("UPDATE orders SET status = #{status} WHERE id = #{id}")
    public boolean updateStatus(@Param("id")int id,@Param("status")int status);
    
    /**
     * @Author GuiGhost
     * @Description //TODO 删除订单
     * @Date 17:16 2020/12/17
     * @Param [id]
     * @return boolean
     **/
    @Delete("DELETE FROM orders WHERE id = #{id}")
    public boolean delete(@Param("id")int id);
}
