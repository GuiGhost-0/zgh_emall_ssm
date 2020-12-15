package com.dao;

import com.entity.Carts;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @InterfaceName CartsDao
 * @Description TODO 购物车Dao层
 * @Author GuiGhost
 * @Date 2020/12/07 15:38
 * @Version 1.0
 **/
public interface CartsDao {

    /**
     * @Author GuiGhost
     * @Description //TODO 根据购物车id查询
     * @Date 11:03 2020/12/11
     * @Param [id]
     * @return com.entity.Carts
     **/
    @Select("SELECT * FROM carts WHERE id = #{id}")
    public Carts selectById(@Param("id")int id);

    /**
     * @Author GuiGhost
     * @Description //TODO  查询指定用户的购物车数量
     * @Date 16:02 2020/12/07
     * @Param [userId]
     * @return int
     **/
    @Select("SELECT IFNULL(SUM(amount),0) FROM carts WHERE user_id=#{userId}")
    int selectSumAmountByUserId(@Param("userId")int userId);

    /**
     * @Author GuiGhost
     * @Description //TODO 查询指定用户购物车商品
     * @Date 22:19 2020/12/09
     * @Param [userId]
     * @return java.util.List<com.entity.Carts>
     **/
    @Select("SELECT * FROM carts WHERE user_id=#{userId}")
    public List<Carts> select(@Param("userId")int userId);

    /**
     * @Author GuiGhost
     * @Description //TODO 添加数据到购物车
     * @Date 23:09 2020/12/09
     * @Param [carts]
     * @return boolean
     **/
    @Insert("INSERT INTO carts(amount, good_id, user_id) " +
            "VALUES (#{amount}, #{goodId}, #{userId})")
    @SelectKey(keyProperty = "id",statement = "SELECT LAST_INSERT_ID()",
            before = false,resultType = Integer.class)
    public boolean insert(Carts carts);

    /**
     * @Author GuiGhost
     * @Description //TODO 根据用户id和商品id查询
     * @Date 23:17 2020/12/09
     * @Param [userId, goodId]
     * @return com.entity.Carts
     **/
    @Select("SELECT * FROM carts WHERE user_id=#{userId} AND good_id=#{goodId} LIMIT 1")
    public Carts selectCartByUserIdAndGoodId(@Param("userId")int userId,@Param("goodId") int goodId);

    /**
     * @Author GuiGhost
     * @Description //TODO 增加指定购物车商品数量
     * @Date 23:41 2020/12/09
     * @Param [id, amount]
     * @return boolean
     **/
    @Update("UPDATE carts SET amount = amount + #{amount} WHERE id = #{id}")
    public boolean updateAddAmount(@Param("id") int id,@Param("amount")int amount);


    /**
     * @Author GuiGhost
     * @Description //TODO 减少指定购物车商品数量
     * @Date 01:02 2020/12/10
     * @Param [id, amount]
     * @return boolean
     **/
    @Update("UPDATE carts SET amount = amount - #{amount} WHERE id = #{id}")
    public boolean updateLessAmount(@Param("id")int id,@Param("amount")int amount);

    /**
     * @Author GuiGhost
     * @Description //TODO 根据id删除购物车商品
     * @Date 01:10 2020/12/10
     * @Param [id]
     * @return boolean
     **/
    @Delete("DELETE FROM carts WHERE id=#{id}")
    public boolean delete(@Param("id")int id);
    
    
    /**
     * @Author GuiGhost
     * @Description //TODO 清空用户购物车
     * @Date 16:26 2020/12/11
     * @Param [userId]
     * @return boolean
     **/
    @Delete("DELETE FROM carts WHERE user_id = #{userId}")
    public boolean clean(@Param("userId") int userId);
}
