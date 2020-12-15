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

    public Tops getByGoodIdAndType(int goodId,byte type){
        return topsDao.selectByGoodIdAndType(goodId,type);
    }
}
