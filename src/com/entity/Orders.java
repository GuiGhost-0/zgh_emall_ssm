package com.entity;

import java.util.Date;
import java.util.List;

/**
 * @ClassName Orders
 * @Description TODO 订单实体类
 * @Author GuiGhost
 * @Date 2020/12/10 21:54
 * @Version 1.0
 **/
public class Orders {

    public static final byte STATUS_NOPAY = 1;//订单状态：未支付
    public static final byte STATUS_PAYED = 2;//订单状态：已支付
    public static final byte STATUS_SEND = 3;//订单状态：配送中
    public static final byte STATUS_FINISH = 4;//订单状态：已完成


    private static final byte PAYTYPE_WECHAT = 1;//支付方式：微信
    private static final byte PAYTYPE_ALIPAY = 2;//支付方式：支付宝
    private static final byte PAYTYPE_OFFLINE = 3;//支付方式：线下

    private Integer id;//订单编号
    private Integer total;//订单金额
    private Integer amount;//商品总数
    private byte status;//订单状态(1未付款/2已付款/3已发货/4已完成)
    private byte paytype;//支付方式 (1微信/2支付宝)
    private String name;//用户名
    private String phone;//用户电话
    private String address;//收获地址
    private Date systime;//下单时间
    private Integer userId;//用户编号
    private List<Items> itemList;//订单项集合
    private Users user;//用户


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public byte getPaytype() {
        return paytype;
    }

    public void setPaytype(byte paytype) {
        this.paytype = paytype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Date getSystime() {
        return systime;
    }

    public void setSystime(Date systime) {
        this.systime = systime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<Items> getItemList() {
        return itemList;
    }

    public void setItemList(List<Items> itemList) {
        this.itemList = itemList;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", total=" + total +
                ", amount=" + amount +
                ", status=" + status +
                ", paytype=" + paytype +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", systime=" + systime +
                ", userId=" + userId +
                '}';
    }
}
