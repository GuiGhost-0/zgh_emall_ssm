package com.dao;

import com.entity.Tops;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;

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


    /**
     * @Author GuiGhost
     * @Description //TODO 加入今日推荐
     * @Date 15:45 2020/12/17
     * @Param [tops]
     * @return boolean
     **/
    @Insert("INSERT INTO `emall`.`tops` (`type`, `good_id`) VALUES (1, #{goodId})")
    @SelectKey(keyProperty = "id",statement = "SELECT LAST_INSERT_ID()",
    before = false,resultType = Integer.class)
    public boolean insert(@Param("goodId")int goodId);


    /**
     * @Author GuiGhost
     * @Description //TODO 移除今日推荐
     * @Date 16:17 2020/12/17
     * @Param [goodId]
     * @return boolean
     **/
    @Delete("DELETE FROM tops WHERE good_id=#{goodId}")
    public boolean delete(@Param("goodId")int goodId);
}
