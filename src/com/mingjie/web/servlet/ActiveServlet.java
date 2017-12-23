package com.mingjie.web.servlet;

import com.mingjie.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author:Liweijian
 * @Description: 激活Servlet
 * @Date:Create in 18:08 2017/12/23 0023
 */
@WebServlet(name = "ActiveServlet")
public class ActiveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String activeCode = request.getParameter("activeCode");

        UserService service = new UserService();
        service.active(activeCode);

        response.sendRedirect(request.getContextPath()+"/login.jsp");
    }
}
