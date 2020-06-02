package com.mingjie.dao;

import com.mingjie.domain.User;
import com.mingjie.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;

/**
 * @Author:Liweijian
 * @Description:
 */
public class UserDao {
    public int register(User user) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

        String sql = "insert into user values (?,?,?,?,?,?,?,?,?,?)";
        int row = (int)runner.update(sql, user.getUid().replaceAll("-",""), user.getUsername(), user.getPassword(),
                user.getName(), user.getEmail(), user.getTelephone(), user.getBirthday(), user.getSex(),
                user.getState(), user.getCode());

        return row;
    }

    public void active(String activeCode) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

        String sql = "update user set state=1 where code=?";
        runner.update(sql,activeCode);
    }

    public Long checkUsername(String username) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

        String sql = "select count(*) from user where username=?";
        Long row = (Long) runner.query(sql, new ScalarHandler(), username);

        return row;
    }

    public User login(String username, String password) throws SQLException {

        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

        String sql = "select * from user where username=? and password=?";
        User user = runner.query(sql, new BeanHandler<User>(User.class), username, password);
        return user;
    }
}
