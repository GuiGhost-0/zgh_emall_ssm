package com.service;

import com.dao.UserDao;
import com.entity.Users;
import com.utils.SafeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户服务
 * */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    /**
     * 获取登录数据
     * */
    public Users getUsernameAndPassword(String username,String password){
        return userDao.getUsernameAndPassword(username, SafeUtil.encode(password));
    }

    /**
     * 注册用户
     * */
    public boolean insertUsers(Users users){
        users.setPassword(SafeUtil.encode(users.getPassword()));
        return userDao.insert(users);
    }


    /**
     * 根据用户名查询用户
     * */
    public Users getByUsername(String username){
        return userDao.getByUsername(username);
    }


    /**
     * 根据ID查询业务
     * */
    public Users getById(int id){
        return userDao.getById(id);
    }

    /**
     * 修改密码业务
     * */
    public boolean updatePassword(int id,String password){
        return userDao.updatePassword(id,SafeUtil.encode(password));
    }

    /**
     * 修改收货信息
     * */
    public boolean updateAddress(int id,Users users){
        users.setId(id);
        return userDao.updateAddress(users);
    }


    /**
     * @Author GuiGhost
     * @Description //TODO 查询所有用户记录
     * @Date 15:00 2020/12/15
     * @Param []
     * @return java.util.List<com.entity.Users>
     **/
    public List<Users> getList(int page,int size){
        return userDao.select(size * (page - 1),size);
    }

    
    /**
     * @Author GuiGhost
     * @Description //TODO 查询用户表总记录数
     * @Date 15:04 2020/12/15
     * @Param []
     * @return long
     **/
    public long getCount(){
        return userDao.selectCount();
    }
}
