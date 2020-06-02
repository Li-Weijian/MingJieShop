package com.mingjie.service;

import com.mingjie.dao.CategoryListDao;
import com.mingjie.domain.Category;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author:Liweijian
 * @Description:
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

    //添加分类
    public void addCategory(Category category) throws SQLException {
        CategoryListDao dao = new CategoryListDao();
        dao.addCategort(category);

    }

    //查找分类
    public Category findCategoryById(String cid) throws SQLException {
        CategoryListDao dao = new CategoryListDao();
        return dao.findCategoryById(cid);
    }

    //编辑分类
    public void updateCategoryById(Category category) throws SQLException {
        CategoryListDao dao = new CategoryListDao();
        dao.updateCategoryById(category);

    }

    //根据id删除分类
    public void deleteCategoryById(String cid) throws SQLException {

        CategoryListDao dao = new CategoryListDao();
        dao.deleteCategoryById(cid);
    }
}
