package com.mingjie.dao;

import com.mingjie.domain.Order;
import com.mingjie.domain.OrderItem;
import com.mingjie.domain.Product;
import com.mingjie.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import javax.xml.crypto.Data;
import java.sql.Connection;
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

    public Product findProductByPid(String pid) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from product where pid = ?";
        Product product = runner.query(sql, new BeanHandler<Product>(Product.class), pid);
        return product;
    }

    //添加订单
    //向orders表插入数据
    public void addOrder(Order order) throws SQLException {
        QueryRunner runner = new QueryRunner();
//        String sql = "insert into orders(oid,total,state,address,name,telephone,uid) values(?,?,?,?,?,?,?)";
        String sql = "insert into orders values(?,?,?,?,?,?,?,?)";
        Connection conn = DataSourceUtils.getConnection();
        runner.update(conn,sql, order.getOid(),order.getOrdertime(),order.getTotal(),order.getState(),
                order.getAddress(),order.getName(),order.getTelephone(),order.getUser().getUid());
    }

    //添加订单项
    public void addOrderItem(List<OrderItem> orderItems) throws SQLException {

        QueryRunner runner = new QueryRunner();
        Connection connection = DataSourceUtils.getConnection();
        String sql = "insert into orderitem values(?,?,?,?,?)";

        for (OrderItem orderItem : orderItems){
            runner.update(connection,sql,orderItem.getItemid(),orderItem.getCount(),orderItem.getSubtotal(),
                    orderItem.getProduct().getPid(),orderItem.getOrder().getOid());
        }
    }

    //更新订单信息
    public void updateOrder(Order order) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

        String sql = "update orders set address=?, name=?,telephone=? where oid=?";
        runner.update(sql,order.getAddress(),order.getName(),order.getTelephone(),order.getOid());
    }

    //更新支付状态
    public void updateOrderState(String r6_order) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

        String sql = "update orders set state = ? where oid = ?";
        runner.update(sql,1,r6_order);
    }
}
