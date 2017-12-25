package com.mingjie.service;

import com.mingjie.dao.CategoryListDao;
import com.mingjie.domain.Category;
import com.mingjie.web.servlet.CategoryListServlet;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author:Liweijian
 * @Description:
 * @Date:Create in 21:44 2017/12/24 0024
 */
public class CategoryListService {


    //查询所有商品类别
    public List<Category> findAllCategory() {
        CategoryListDao dao = new CategoryListDao();
        List<Category> categoryList = null;
        try {
            categoryList = dao.findAllCategory();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryList;
    }
}
