package com.mingjie.web.servlet;

import com.mingjie.domain.PageBean;
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
 * @Description:
 * @Date:Create in 22:31 2017/12/24 0024
 */
@WebServlet(name = "ProductByCidServlet")
public class ProductByCidServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");
        String currentPageStr = request.getParameter("currentPage");
        if (currentPageStr == null) currentPageStr = "1"; //如果为空，则默认第一页
        int currentPage = Integer.parseInt(currentPageStr);
        int currentCount = 12;

        ProductService service = new ProductService();
        PageBean<Product> pageBean = service.findProductByCid(cid,currentPage,currentCount);

        request.setAttribute("cid",cid);
        request.setAttribute("pageBean",pageBean);
        request.getRequestDispatcher(request.getContextPath() + "/product_list.jsp").forward(request,response);

    }
}
