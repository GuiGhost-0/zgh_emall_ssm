package com.dao;

import com.entity.Types;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @InterfaceName TypesDao
 * @Description TODO  商品类别Dao
 * @Author GuiGhost
 * @Date 2020/12/07 14:37
 * @Version 1.0
 **/
public interface TypesDao {

    /*
     * @Author GuiGhost
     * @Description //TODO 根据num排序字段查询商品类别表所有信息
     * @Date 14:43 2020/12/07
     * @Param []
     * @return java.util.List<com.entity.Types>
     **/
    @Select("SELECT * FROM types ORDER BY num")
    public List<Types> selectTypesList();

    /**
     * @Author GuiGhost
     * @Description //TODO 根据类目Id查询
     * @Date 09:43 2020/12/08
     * @Param [id]
     * @return com.entity.Types
     **/
    @Select("SELECT * FROM types WHERE id=#{id}")
    public Types select(@Param("id")int id);


    /**
     * @Author GuiGhost
     * @Description //TODO 添加类目信息
     * @Date 15:28 2020/12/15
     * @Param [types]
     * @return boolean
     **/
    @Insert("INSERT INTO `emall`.`types` (`name`, `num`) VALUES (#{name},#{num})")
    @SelectKey(keyProperty = "id",statement = "SELECT LAST_INSERT_ID()",
    before = false,resultType = Integer.class)
    public boolean insert(Types types);


    /**
     * @Author GuiGhost
     * @Description //TODO 修改类目信息
     * @Date 15:47 2020/12/15
     * @Param [types]
     * @return boolean
     **/
    @Update("UPDATE `emall`.`types` SET `name` = #{name}, `num` = #{num} WHERE `id` = #{id}")
    public boolean update(Types types);

    /**
     * @Author GuiGhost
     * @Description //TODO 删除指定类目信息
     * @Date 16:02 2020/12/15
     * @Param [id]
     * @return boolean
     **/
    @Delete("DELETE FROM types WHERE id=#{id}")
    public boolean delete(@Param("id")int id);
}
