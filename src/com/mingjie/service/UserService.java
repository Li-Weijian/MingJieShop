package com.mingjie.service;

import com.mingjie.dao.UserDao;
import com.mingjie.domain.User;

import java.sql.SQLException;

/**
 * @Author:Liweijian
 * @Description:
 * @Date:Create in 16:51 2017/12/23 0023
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
}
