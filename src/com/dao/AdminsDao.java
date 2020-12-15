package com.dao;

import com.entity.Admins;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @InterfaceName AdminsDao
 * @Description TODO 后台管理员dao类
 * @Author GuiGhost
 * @Date 2020/12/12 23:28
 * @Version 1.0
 **/
public interface AdminsDao {

    /**
     * @Author GuiGhost
     * @Description //TODO 后台管理员验证
     * @Date 00:47 2020/12/13
     * @Param [admins]
     * @return com.entity.Admins
     **/
    @Select("SELECT * FROM admins " +
            "WHERE username=#{username} AND password=#{password}")
    public Admins checkout(Admins admins);
    
    /**
     * @Author GuiGhost
     * @Description //TODO 查询所有后台管理员
     * @Date 00:54 2020/12/13
     * @Param []
     * @return java.util.List<com.entity.Admins>
     **/
    @Select("SELECT * FROM admins")
    public List<Admins> select();

    
    /**
     * @Author GuiGhost
     * @Description //TODO 添加管理员
     * @Date 01:04 2020/12/13
     * @Param [admins]
     * @return boolean
     **/
    @Insert("INSERT INTO `emall`.`admins`(`username`, `password`) " +
            "VALUES (#{username}, #{password})")
    @SelectKey(keyProperty = "id",statement = "SELECT LAST_INSERT_ID()",
    before = false,resultType = Integer.class)
    public boolean insert(Admins admins);


    /**
     * @Author GuiGhost
     * @Description //TODO 删除指定管理员
     * @Date 01:37 2020/12/13
     * @Param [id]
     * @return boolean
     **/
    @Delete("DELETE FROM admins WHERE id=#{id}")
    public boolean delete(@Param("id") int id);
}
