package com.entity;

/**
 * @ClassName Items
 * @Description TODO 支付实体类
 * @Author GuiGhost
 * @Date 2020/12/10 21:45
 * @Version 1.0
 **/
public class Items {
    private Integer id;//id
    private Integer price;//购买时价格
    private Integer amount;//购买时数量
    private Integer orderId;//订单编号
    private Integer goodId;//商品编号
    private Goods good;//商品

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }

    public Goods getGood() {
        return good;
    }

    public void setGood(Goods good) {
        this.good = good;
    }
}
