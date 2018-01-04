package com.mingjie.service;

import com.mingjie.dao.AdminDao;
import com.mingjie.dao.ProductDao;
import com.mingjie.domain.Category;
import com.mingjie.domain.Order;
import com.mingjie.domain.Product;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @Author:Liweijian
 * @Description:
 * @Date:Create in 23:54 2018/1/2 0002
 */
public class AdminService {

    public List<Product> findAllProduct() {

        AdminDao dao = new AdminDao();
        List<Product> productList = null;
        try {
            productList = dao.findAllProduct();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }

    public List<Category> findAllCategory() {
        AdminDao dao = new AdminDao();
        List<Category> categoryList =  null;
        try {
            categoryList = dao.findAllCategory();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryList;
    }

    public void addProduct(Product product) {
        AdminDao dao = new AdminDao();
        try {
            dao.addProduct(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Order> findAllOrder() {
        AdminDao dao = new AdminDao();
        List<Order> orderList = null;
        try {
            orderList = dao.findAllOrder();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    public List<Map<String,Object>> findOrderInfoByOid(String oid) {
        AdminDao dao = new AdminDao();
        List<Map<String,Object>> mapList = null;
        try {
            mapList = dao.findOrderInfoByOid(oid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mapList;
    }

    public List<Category> showAllCategory() {
        AdminDao dao = new AdminDao();
        List<Category> categoryList = null;
        try {
            categoryList = dao.showAllCategory();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categoryList;
    }
}
