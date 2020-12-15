package com.entity;

import org.springframework.jmx.export.naming.IdentityNamingStrategy;

/**
 * @ClassName Carts
 * @Description TODO 购物车实体类
 * @Author GuiGhost
 * @Date 2020/12/07 15:26
 * @Version 1.0
 **/
public class Carts {
    //  `id` int(11) NOT NULL AUTO_INCREMENT,
    //  `amount` int(11) NOT NULL DEFAULT 0 COMMENT '数量',
    //  `good_id` int(11) NOT NULL COMMENT '商品ID',
    //  `user_id` int(11) NOT NULL DEFAULT 0 COMMENT '用户ID',
    private Integer id;//购物车编号
    private Integer amount;//购物车数量
    private Integer goodId;//商品编号
    private Integer userId;//用户编号
    private Goods good;//商品
    private Integer total;//总金额

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Goods getGood() {
        return good;
    }

    public void setGood(Goods good) {
        this.good = good;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Carts{" +
                "id=" + id +
                ", amount=" + amount +
                ", goodId=" + goodId +
                ", userId=" + userId +
                '}';
    }
}
