package com.dao;

import com.entity.Tops;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @InterfaceName TopsDao
 * @Description TODO 今日推荐Dao类
 * @Author GuiGhost
 * @Date 2020/12/08 09:34
 * @Version 1.0
 **/
public interface TopsDao {

    /**
     * @Author GuiGhost
     * @Description //TODO 根据商品id和是否今日推荐查询
     * @Date 09:49 2020/12/08
     * @Param [goodId, type]
     * @return com.entity.Tops
     **/
    @Select("SELECT * FROM tops WHERE good_id=#{goodId} AND type=#{type}")
    public Tops selectByGoodIdAndType(@Param("goodId")int goodId,@Param("type")byte type);
}
