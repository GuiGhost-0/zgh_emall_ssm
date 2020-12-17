package com.service;

import com.dao.TopsDao;
import com.entity.Tops;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName TopsService
 * @Description TODO 今日推荐服务类
 * @Author GuiGhost
 * @Date 2020/12/08 09:36
 * @Version 1.0
 **/
@Service
public class TopsService {
    @Autowired
    private TopsDao topsDao;

    /**
     * @Author GuiGhost
     * @Description //TODO 根据商品编号查询今日推荐商品
     * @Date 15:47 2020/12/17
     * @Param [goodId, type]
     * @return com.entity.Tops
     **/
    public Tops getByGoodIdAndType(int goodId,byte type){
        return topsDao.selectByGoodIdAndType(goodId,type);
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 添加今日推荐商品
     * @Date 15:52 2020/12/17
     * @Param [goodId]
     * @return boolean
     **/
    public boolean add(int goodId){
        return topsDao.insert(goodId);
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 移除今日推荐商品
     * @Date 16:17 2020/12/17
     * @Param [goodId]
     * @return boolean
     **/
    public boolean remove(int goodId){
        return topsDao.delete(goodId);
    }
}
