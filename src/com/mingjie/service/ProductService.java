package com.mingjie.service;

import com.mingjie.dao.ProductDao;
import com.mingjie.domain.Order;
import com.mingjie.domain.PageBean;
import com.mingjie.domain.Product;
import com.mingjie.utils.DataSourceUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @Author:Liweijian
 * @Description:
 * @Date:Create in 20:08 2017/12/24 0024
 */
public class ProductService {

    //查询热门商品
    public List<Product> findHotProduct() {

        ProductDao dao = new ProductDao();
        List<Product> hotProductList = null;

        try {
            hotProductList = dao.findHotProduct();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hotProductList;
    }

    //查询最新商品
    public List<Product> findNewProduct() {
        ProductDao dao = new ProductDao();
        List<Product> newProductList = null;

        try {
            newProductList = dao.findNewProduct();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return newProductList;
    }

    public PageBean<Product> findProductByCid(String cid, int currentPage, int currentCount) {

        ProductDao dao = new ProductDao();
        PageBean<Product> pageBean = new PageBean<>();

        //1.封装当前页
        pageBean.setCurrentPage(currentPage);

        //2.封装每页显示的条数
        pageBean.setCurrentCount(currentCount);

        //3.封装总条数
        int totalCount = 0;
        try {
            totalCount = dao.getCount(cid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        pageBean.setTotalCount(totalCount);

        //4.封装总页数
        int totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);
        pageBean.setTotalPage(totalPage);

        //5.封装查询的Product
        List<Product> productList = null;
        int index = (currentPage-1)*currentCount;
        try {
            productList = dao.findProductByCid(cid,index,currentCount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        pageBean.setList(productList);

        return pageBean;
    }

    public Product findProductByPid(String pid) {
        ProductDao dao = new ProductDao();
        Product product = null;
        try {
              product = dao.findProductByPid(pid);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }

    //提交订单
    public void submitOrder(Order order) {
        /*提交订单需要更新两个表，所以需要事务控制*/

        ProductDao dao = new ProductDao();

        //1.开启事务
        try {
            DataSourceUtils.startTransaction();
            //更新Orders表
            dao.addOrder(order);
            //更新OrderItem表
            dao.addOrderItem(order.getOrderItems());

        } catch (SQLException e) {
            try {
                DataSourceUtils.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            try {
                //提交事务
                DataSourceUtils.commitAndRelease();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //更新订单信息
    public void updateOrder(Order order) {

        ProductDao dao = new ProductDao();
        try {
            dao.updateOrder(order);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //修改支付状态
    public void updateOrderState(String r6_order) {
        ProductDao dao = new ProductDao();
        try {
            dao.updateOrderState(r6_order);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //查询指定用户的所有订单
    public List<Order> findAllOrders(String uid) {

        ProductDao dao = new ProductDao();
        List<Order> orderList = null;
        try {
            return dao.findAllOrders(uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    public List<Map<String, Object>> findOrderItemsByOid(String oid) {
        ProductDao dao = new ProductDao();
        List<Map<String, Object>> mapList = null;
        try {
            mapList = dao.findOrderItemsByOid(oid);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mapList;
    }


}
