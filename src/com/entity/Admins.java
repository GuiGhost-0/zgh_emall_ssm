package com.entity;

import com.utils.SystemStringUtils;

/**
 * @ClassName Admins
 * @Description TODO 后台管理员实体类
 * @Author GuiGhost
 * @Date 2020/12/12 23:25
 * @Version 1.0
 **/
public class Admins {
    private Integer id;//管理员编号
    private String username;//用户名
    private String password;//密码


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
}
