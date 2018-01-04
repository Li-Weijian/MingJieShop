package com.mingjie.dao;

import com.mingjie.domain.Category;
import com.mingjie.domain.Order;
import com.mingjie.domain.Product;
import com.mingjie.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.omg.CORBA.ORB;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @Author:Liweijian
 * @Description:
 * @Date:Create in 23:56 2018/1/2 0002
 */
public class AdminDao {
    public List<Product> findAllProduct() throws SQLException {

        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from product";
        List<Product> productList = runner.query(sql, new BeanListHandler<Product>(Product.class));
        return productList;
    }

    public List<Category> findAllCategory() throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from category";
        List<Category> categoryList = runner.query(sql, new BeanListHandler<Category>(Category.class));


        return categoryList;
    }

    public void addProduct(Product product) throws SQLException {

        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

        String sql = "insert into product values(?,?,?,?,?,?,?,?,?,?)";
        runner.update(sql,product.getPid(),product.getPname(),product.getMarket_price(),product.getShop_price(),
                product.getPimage(),product.getPdate(),product.getIs_hot(),product.getPdesc(),product.getPflag(),product.getCategory().getCid());
    }

    public List<Order> findAllOrder() throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from orders";
        List<Order> orderList = runner.query(sql, new BeanListHandler<Order>(Order.class));
        return orderList;

    }

    public List<Map<String,Object>> findOrderInfoByOid(String oid) throws SQLException {

        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select p.pimage,p.pname,p.shop_price,i.count,i.subtotal from product p,orderitem i where p.pid = i.pid and oid=?";
        List<Map<String, Object>> mapList = runner.query(sql, new MapListHandler(), oid);
        return mapList;
    }
}
