package com.mingjie.domain;

import java.util.Date;

/**
 * @Author:Liweijian
 * @Description:
 * @Date:Create in 16:38 2017/12/23 0023
 */

public class User {
/*    `uid` varchar(32) NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
            `sex` varchar(10) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `code` varchar(64) DEFAULT NULL,*/

    private String uid;
    private String username;
    private String password;
    private String name;
    private String email;
    private String telephone;
    private Date birthday;
    private String sex;
    private int state;      //是否激活
    private String code;   //激活码

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUid() {
        return uid;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public Date getBirthday() {
        return birthday;
    }

    public int getState() {
        return state;
    }

    public String getCode() {
        return code;
    }
}
