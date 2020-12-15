package com.service;

import com.dao.TypesDao;
import com.entity.Types;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName TypesService
 * @Description TODO 商品类别服务类
 * @Author GuiGhost
 * @Date 2020/12/07 14:44
 * @Version 1.0
 **/
@Service
public class TypesService {
    @Autowired
    private TypesDao typesDao;

    /*
     * @Author GuiGhost
     * @Description //TODO 获取列表
     * @Date 14:45 2020/12/07
     * @Param []
     * @return java.util.List<com.entity.Types>
     **/
    public List<Types> getList(){
        return typesDao.selectTypesList();
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 根据ID值查询列表
     * @Date 09:44 2020/12/08
     * @Param [id]
     * @return com.entity.Types
     **/
    public Types get(int id){
        return typesDao.select(id);
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 添加商品类目业务
     * @Date 15:28 2020/12/15
     * @Param [types]
     * @return boolean
     **/
    public boolean save(Types types){
        return typesDao.insert(types);
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 修改类目信息业务
     * @Date 15:48 2020/12/15
     * @Param [types]
     * @return boolean
     **/
    public boolean update(Types types){
        return typesDao.update(types);
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 删除指定类目信息
     * @Date 16:02 2020/12/15
     * @Param [id]
     * @return boolean
     **/
    public boolean delete(int id){
        return typesDao.delete(id);
    }
}
