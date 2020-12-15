package com.entity;

import com.utils.SystemStringUtils;

/**
 * 用户实体类
 * */
public class Users {
    //`id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    //  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
    //  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
    //  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货人',
    //  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货电话',
    //  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货地址',
    private Integer id;//用户编号
    private String username;//用户名
    private String password;//密码
    private String name;//收货人
    private String phone;//收货电话
    private String address;//收货地址

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = SystemStringUtils.strNotNullTrim(username);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = SystemStringUtils.strNotNullTrim(password);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = SystemStringUtils.strNotNullTrim(name);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = SystemStringUtils.strNotNullTrim(phone);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = SystemStringUtils.strNotNullTrim(address);
    }


    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
