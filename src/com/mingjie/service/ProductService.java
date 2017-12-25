package com.mingjie.service;

import com.mingjie.dao.ProductDao;
import com.mingjie.domain.PageBean;
import com.mingjie.domain.Product;

import java.sql.SQLException;
import java.util.List;

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
}
