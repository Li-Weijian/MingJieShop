package com.mingjie.web.servlet;

import com.google.gson.Gson;
import com.mingjie.domain.Category;
import com.mingjie.domain.Product;
import com.mingjie.service.AdminService;
import com.mingjie.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author:Liweijian
 * @Description:
 * @Date:Create in 23:50 2018/1/2 0002
 */
@WebServlet(name = "AdminServlet")
public class AdminServlet extends BaseServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    //后台展示商品列表
    public void showProductListAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        AdminService service = new AdminService();
        List<Product> productList = service.findAllProduct();

        request.setAttribute("productList",productList);
        request.getRequestDispatcher("admin/product/list.jsp").forward(request,response);
    }

    //显示全部类别
    public void findAllCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        AdminService service = new AdminService();
        List<Category> categoryList = service.findAllCategory();
        //封装成json
        Gson gson = new Gson();
        String json = gson.toJson(categoryList);
        //返回到客户端
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(json);

    }

    //添加商品
    public void addProductAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        




    }






}
