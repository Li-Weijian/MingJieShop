package com.mingjie.dao;

import com.mingjie.domain.Product;
import com.mingjie.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author:Liweijian
 * @Description:
 * @Date:Create in 20:14 2017/12/24 0024
 */
public class ProductDao {

    //查询热门商品
    public List<Product> findHotProduct() throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from product where is_hot = 1 limit 0,9";
        return runner.query(sql, new BeanListHandler<Product>(Product.class));
    }

    public List<Product> findNewProduct() throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from product order by pdate limit 0,9";
        return runner.query(sql, new BeanListHandler<Product>(Product.class));
    }

    public List<Product> findProductByCid(String cid, int index, int currentCount) throws SQLException {

        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from product where cid=? limit ?,?";
        List<Product> productList = runner.query(sql, new BeanListHandler<Product>(Product.class), cid,index,currentCount);
        return productList;
    }

    public int getCount(String cid) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select count(*) from product where cid = ?";
        Long row = (Long) runner.query(sql, new ScalarHandler(), cid);
        return row.intValue();
    }
}
