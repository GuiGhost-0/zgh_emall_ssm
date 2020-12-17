package com.dao;

import com.entity.Goods;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @InterfaceName GoodsDao
 * @Description TODO 商品Dao类
 * @Author GuiGhost
 * @Date 2020/12/08 09:35
 * @Version 1.0
 **/
public interface GoodsDao {


    /**
     * @Author GuiGhost
     * @Description //TODO 根据商品id查询
     * @Date 16:45 2020/12/09
     * @Param [id]
     * @return com.entity.Goods
     **/
    @Select("SELECT * FROM goods WHERE id=#{id}")
    public Goods selectById(@Param("id")int id);

    /**
     * @Author GuiGhost
     * @Description //TODO 查询所有的商品并分页
     * @Date 10:43 2020/12/08
     * @Param [begin, size]
     * @return java.util.List<com.entity.Goods>
     **/
    @Select("SELECT * FROM goods ORDER BY id DESC LIMIT #{begin},#{size}")
    public List<Goods> selectList(@Param("begin")int begin,@Param("size")int size);

    /**
     * @Author GuiGhost
     * @Description //TODO 根据类目Id查询商品并分页
     * @Date 10:15 2020/12/08
     * @Param [typeId, begin, size]
     * @return java.util.List<com.entity.Goods>
     **/
    @Select("SELECT * FROM goods WHERE type_id=#{typeId} LIMIT #{begin},#{size}")
    public List<Goods> selectListByTypeId(@Param("typeId")int typeId,
                                          @Param("begin")int begin,
                                          @Param("size")int size);


    /**
     * @Author GuiGhost
     * @Description //TODO 查询商品总数
     * @Date 10:57 2020/12/08
     * @Param []
     * @return int
     **/
    @Select("SELECT COUNT(*) FROM goods")
    public int selectCount();


    /**
     * @Author GuiGhost
     * @Description //TODO 查询该类目ID的商品总数
     * @Date 10:55 2020/12/08
     * @Param [typeId]
     * @return int
     **/
    @Select("SELECT COUNT(*) FROM goods WHERE type_id=#{typeId}")
    public int selectCountByTypeId(@Param("typeId")int typeId);

    /**
     * @Author GuiGhost
     * @Description //TODO  连表查询今日推荐
     * @Date 11:50 2020/12/08
     * @Param [type, begin, size]
     * @return java.util.List<com.entity.Goods>
     **/
    @Select("SELECT g.* FROM goods g,tops t WHERE g.id = t.good_id AND t.type = #{type} LIMIT #{begin},#{size}")
    public List<Goods> selectListByTopType(@Param("type")byte type,
                                           @Param("begin")int begin,
                                           @Param("size")int size);

    /**
     * @Author GuiGhost
     * @Description //TODO 连表查询今日推荐总记录数
     * @Date 11:52 2020/12/08
     * @Param [type]
     * @return java.util.List<com.entity.Goods>
     **/
    @Select("SELECT COUNT(*) FROM goods g,tops t WHERE g.id = t.good_id AND t.type = #{type}")
    public int selectCountByTopType(@Param("type")byte type);


    /**
     * @Author GuiGhost
     * @Description //TODO 热销商品查询
     * @Date 14:03 2020/12/08
     * @Param [begin, size]
     * @return java.util.List<com.entity.Goods>
     **/
    @Select("SELECT * FROM goods where sales > 0 ORDER BY sales DESC LIMIT #{begin},#{size}")
    public List<Goods> selectListByHot(@Param("begin")int begin,
                                       @Param("size")int size);

    /**
     * @Author GuiGhost
     * @Description //TODO 热销商品总记录
     * @Date 14:22 2020/12/08
     * @Param []
     * @return int
     **/
    @Select("SELECT COUNT(*) FROM goods where sales > 0")
    public int selectCountByHot();


    /**
     * @Author GuiGhost
     * @Description //TODO 新品查询
     * @Date 16:56 2020/12/08
     * @Param [begin, size]
     * @return java.util.List<com.entity.Goods>
     **/
    @Select("SELECT * FROM goods ORDER BY id DESC LIMIT #{begin},#{size}")
    public List<Goods> selectListByNew(@Param("begin")int begin,@Param("size")int size);


    /**
     * @Author GuiGhost
     * @Description //TODO 新品总记录数
     * @Date 17:00 2020/12/08
     * @Param []
     * @return int
     **/
    @Select("SELECT COUNT(*) FROM goods ORDER BY id DESC")
    public int selectCountByNew();

    /**
     * @Author GuiGhost
     * @Description //TODO 修改商品库存
     * @Date 15:48 2020/12/11
     * @Param [id, amount]
     * @return boolean
     **/
    @Update("UPDATE goods SET stock = stock - #{amount} WHERE id=#{id}")
    public boolean updateStock(@Param("id")int id,@Param("amount")int amount);

    @Update("UPDATE goods SET stock = stock + #{amount} WHERE id=#{id}")
    public boolean addStock(@Param("id")int id,@Param("amount")int amount);
    
    /**
     * @Author GuiGhost
     * @Description //TODO 修改商品销量
     * @Date 16:51 2020/12/11
     * @Param [id, amount]
     * @return boolean
     **/
    @Update("UPDATE goods SET sales = sales + #{amount} WHERE id=#{id}")
    public boolean updateSales(@Param("id")int id,@Param("amount")int amount);

    @Update("UPDATE goods SET sales = sales - #{amount} WHERE id=#{id}")
    public boolean lessSales(@Param("id")int id,@Param("amount")int amount);

    /**
     * @Author GuiGhost
     * @Description //TODO 添加商品
     * @Date 16:39 2020/12/15
     * @Param [good]
     * @return boolean
     **/
    @Insert("INSERT INTO `emall`.`goods` (`cover`, `name`, `intro`, `spec`, `price`, `stock`, `sales`, `content`, `type_id`) " +
            "VALUES (#{cover}, #{name}, #{intro}, #{spec}, #{price}, #{stock}, #{sales}, #{content}, #{typeId})")
    @SelectKey(keyProperty = "id",statement = "SELECT LAST_INSERT_ID()",
    before = false,resultType = Integer.class)
    public boolean insert(Goods good);


    /**
     * @Author GuiGhost
     * @Description //TODO 修改商品
     * @Date 22:11 2020/12/15
     * @Param [good]
     * @return boolean
     **/
    @Update("UPDATE `emall`.`goods` SET `cover` = #{cover}, `name` = #{name}, `intro` = #{intro}, `spec` = #{spec}, `price` = #{price}, `stock` = #{stock}, `sales` = #{sales}, `content` = #{content}, `type_id` = #{typeId} WHERE `id` = #{id}")
    public boolean update(Goods good);
    
    /**
     * @Author GuiGhost
     * @Description //TODO 删除商品
     * @Date 16:22 2020/12/17
     * @Param [goodId]
     * @return boolean
     **/
    @Delete("DELETE FROM goods WHERE id=#{goodId}")
    public boolean delete(@Param("goodId")int goodId);

    /**
     * @Author GuiGhost
     * @Description //TODO 模糊查询
     * @Date 23:33 2020/12/17
     * @Param [search]
     * @return java.util.List<com.entity.Goods>
     **/
    @Select("SELECT * FROM goods WHERE `name` LIKE #{search}")
    public List<Goods> search(@Param("search") String search);
}
