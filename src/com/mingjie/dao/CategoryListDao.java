package com.mingjie.dao;

import com.mingjie.domain.Category;
import com.mingjie.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author:Liweijian
 * @Description:
 */
public class CategoryListDao {
    public List<Category> findAllCategory() throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from category";
        List<Category> categoryList = runner.query(sql, new BeanListHandler<Category>(Category.class));
        return categoryList;

    }

    //添加类别
    public void addCategort(Category category) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

        String sql = "insert into category values(?,?)";
        Connection conn = DataSourceUtils.getConnection();
        runner.update(conn,sql, category.getCid(), category.getCname());
    }

    //根据id查找
    public Category findCategoryById(String cid) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from category where cid = ?";
        return runner.query(sql, new BeanHandler<>(Category.class),cid);
    }

    //根据id更新
    public void updateCategoryById(Category category) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

        String sql = "update category SET cname = ? WHERE cid = ?";
        Connection conn = DataSourceUtils.getConnection();
        runner.update(conn,sql, category.getCname() ,category.getCid());
    }

    //根据id删除
    public void deleteCategoryById(String cid) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "DELETE FROM category where cid = ?";
        Connection conn = DataSourceUtils.getConnection();

        runner.update(conn,sql,cid);

    }
}
