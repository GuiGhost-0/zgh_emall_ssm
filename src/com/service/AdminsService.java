package com.service;

import com.dao.AdminsDao;
import com.entity.Admins;
import com.utils.SafeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName AdminsService
 * @Description TODO 后台服务类
 * @Author GuiGhost
 * @Date 2020/12/12 23:45
 * @Version 1.0
 **/
@Service
public class AdminsService {

    @Autowired
    private AdminsDao adminsDao;

    /**
     * @Author GuiGhost
     * @Description //TODO 后台登录检查
     * @Date 00:46 2020/12/13
     * @Param [admins]
     * @return com.entity.Admins
     **/
    public Admins checkout(Admins admins){
        admins.setPassword(SafeUtil.encode(admins.getPassword()));
        return adminsDao.checkout(admins);
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 查询所有后台管理员业务
     * @Date 00:55 2020/12/13
     * @Param []
     * @return java.util.List<com.entity.Admins>
     **/
    public List<Admins> get(){
        return adminsDao.select();
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 新增管理员模块
     * @Date 01:06 2020/12/13
     * @Param [admins]
     * @return boolean
     **/
    public boolean insert(Admins admins){
        admins.setPassword(SafeUtil.encode(admins.getPassword()));
        return adminsDao.insert(admins);
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 删除指定管理员
     * @Date 01:38 2020/12/13
     * @Param [id]
     * @return boolean
     **/
    public boolean delete(int id){
        return adminsDao.delete(id);
    }
}
