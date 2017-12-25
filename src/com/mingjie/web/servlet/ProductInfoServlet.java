package com.mingjie.web.servlet;

import com.mingjie.domain.Product;
import com.mingjie.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
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
        String cid = request.getParameter("cid");
        String currentPage = request.getParameter("currentPage");

        ProductService service = new ProductService();
        Product product = service.findProductByPid(pid);

        //获得客户端携带的Cookie -- 获得名字为pids的Cookie
        String pids = pid;
        Cookie[] cookies = request.getCookies();
        if (cookies!=null){
            for (Cookie cookie : cookies){
                if ("pids".equals(cookie.getName())){
                    pids = cookie.getValue();//2-1-3
                    //1-3-2  本次访问的pid为8： 8-1-3-2
                    //1-3-2  本次访问的pid为2： 2-1-3
                    String[] split = pids.split("-"); //切割：2,1,3
                    List<String> asList = Arrays.asList(split); //转换为集合
                    LinkedList<String> list = new LinkedList<>(asList); //转换为链表集合

                    //如果访问的pid已存在--先删除后添加到头部
                    if (list.contains(pid)){
                        list.remove(pid);
                        list.addFirst(pid);
                    }else {
                        //否则直接添加到头部
                        list.addFirst(pid);
                    }

                    //将3，1，2转换成3-1-2
                    StringBuffer sb = new StringBuffer();
                    for (int i = 0; i < list.size()&&i<7; i++){
                        sb.append(list.get(i));
                        sb.append("-"); // 3-1-2-
                    }
                    //去掉最后的-
                    pids = sb.substring(0, sb.length() - 1);
                }
            }
        }

        Cookie cookie_pids = new Cookie("pids",pids);
        response.addCookie(cookie_pids);


        request.setAttribute("cid",cid);
        request.setAttribute("currentPage",currentPage);
        request.setAttribute("product",product);
        request.getRequestDispatcher(request.getContextPath() + "/product_info.jsp").forward(request,response);
    }
}
