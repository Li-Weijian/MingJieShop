package com.mingjie.web.servlet;

import com.google.gson.Gson;
import com.mingjie.domain.Category;
import com.mingjie.domain.Order;
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
import java.util.Map;

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

    //展示所有订单
    public void showOrderList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        AdminService service = new AdminService();
        List<Order> orderList = service.findAllOrder();

        request.setAttribute("orderList",orderList);
        request.getRequestDispatcher("/admin/order/list.jsp").forward(request,response);

    }

    //订单详情
    public void findOrderInfoByOid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            Thread.sleep(2000);  //休眠两秒钟，提高用户体验
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String oid = request.getParameter("oid");

        AdminService service = new AdminService();
        List<Map<String,Object>> mapList = service.findOrderInfoByOid(oid);

        Gson gson = new Gson();
        String s = gson.toJson(mapList);
        response.setContentType("text/html;charset=UTF-8");  //解决乱码
        response.getWriter().write(s);
    }




}
