package com.mingjie.web.servlet;

import com.mingjie.domain.Product;
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
 * @Description: 首页
 * @Date:Create in 20:06 2017/12/24 0024
 */
@WebServlet(name = "IndexServlet")
public class IndexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ProductService service = new ProductService();
        List<Product> hotProductList = service.findHotProduct();
        List<Product> newProductList = service.findNewProduct();

        request.setAttribute("newProductList",newProductList);
        request.setAttribute("hotProductList", hotProductList);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
