package com.dao;

import com.entity.Users;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 用户Dao
 * */
public interface UserDao {
    /**
     * 登录查询方法
     * */
    @Select("select * from users where username=#{username} and password=#{password}")
    public Users getUsernameAndPassword(@Param("username")String username,@Param("password")String password);


    /**
     * 添加数据方法
     * */
    @Insert("INSERT INTO `emall`.`users`(`username`, `password`, `name`, `phone`, `address`) " +
            "VALUES (#{username}, #{password}, #{name}, #{phone}, #{address})")
    @SelectKey(keyProperty = "id" ,statement = "SELECT LAST_INSERT_ID()",
            before = false,resultType = Integer.class)
    public boolean insert(Users users);


    /**
     * 根据用户名查询用户记录
     * */
    @Select("SELECT * FROM users WHERE username=#{username}")
    public Users getByUsername(String username);

    /**
     * 根据ID查找
     * */
    @Select("SELECT * FROM users WHERE id=#{id}")
    public Users getById(@Param("id") int id);


    /**
     * 修改密码
     * */
    @Update("UPDATE users SET password=#{password} WHERE id=#{id}")
    public boolean updatePassword(@Param("id") int id,@Param("password") String password);

    /**
     * 修改收货信息
     * */
    @Update("UPDATE users SET name=#{name},phone=#{phone},address=#{address} WHERE id=#{id}")
    public boolean updateAddress(Users users);

    /**
     * @Author GuiGhost
     * @Description //TODO 查询所有用户信息
     * @Date 14:58 2020/12/15
     * @Param []
     * @return java.util.List<com.entity.Users>
     **/
    @Select("SELECT * FROM users LIMIT #{begin},#{size}")
    public List<Users> select(@Param("begin")int begin,@Param("size")int size);


    /**
     * @Author GuiGhost
     * @Description //TODO 查询所有用户总记录数
     * @Date 15:01 2020/12/15
     * @Param []
     * @return long
     **/
    @Select("SELECT COUNT(*) FROM users")
    public long selectCount();
}