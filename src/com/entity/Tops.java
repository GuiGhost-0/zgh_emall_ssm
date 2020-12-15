package com.entity;

/**
 * @ClassName Tops
 * @Description TODO 今日推荐表实体类
 * @Author GuiGhost
 * @Date 2020/12/08 09:30
 * @Version 1.0
 **/
public class Tops {

    public static final byte TYPE_TODAY = 1;

    //  `id` int(11) NOT NULL AUTO_INCREMENT,
    //  `type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '推荐类型(1今日推荐)',
    //  `good_id` int(11) NOT NULL DEFAULT 0 COMMENT '商品ID',
    private Integer id;//编号
    private byte type;//推荐类型(1今日推荐)
    private Integer goodId;//商品id

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }
}
