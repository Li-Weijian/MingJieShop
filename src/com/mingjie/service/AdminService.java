package com.mingjie.service;

import com.mingjie.dao.AdminDao;
import com.mingjie.dao.ProductDao;
import com.mingjie.domain.Category;
import com.mingjie.domain.Product;

import java.sql.SQLException;
import java.util.List;

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
}
