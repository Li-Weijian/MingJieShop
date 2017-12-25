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
 * @Description: 商品详细信息
 * @Date:Create in 10:04 2017/12/25 0025
 */
@WebServlet(name = "ProductInfoServlet")
public class ProductInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pid = request.getParameter("pid");

        ProductService service = new ProductService();
        Product product = service.findProductByPid(pid);
        request.setAttribute("product",product);
        request.getRequestDispatcher(request.getContextPath() + "/product_info.jsp").forward(request,response);


    }
}
