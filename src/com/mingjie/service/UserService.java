package com.mingjie.service;

import com.mingjie.dao.UserDao;
import com.mingjie.domain.User;

import java.sql.SQLException;

/**
 * @Author:Liweijian
 * @Description:
 */
public class UserService {

    //注册
    public boolean register(User user) {

        UserDao dao = new UserDao();
        int row = 0;
        try {
           row = dao.register(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return row > 0 ? true:false;
    }

    //激活邮件
    public void active(String activeCode) {
        UserDao dao = new UserDao();
        try {
            dao.active(activeCode);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //校验用户是否存在
    public boolean checkUsername(String username) {
        UserDao dao = new UserDao();
        Long row = 0L;
        try {
            row = dao.checkUsername(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return row > 0?true:false;
    }

    //用户登录
    public User login(String username, String password) {

        UserDao dao = new UserDao();
        User user = null;

        try {
            user = dao.login(username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}
