package com.mingjie.web.servlet;

import com.mingjie.domain.User;
import com.mingjie.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author:Liweijian
 * @Description: 用户登录
 * @Date:Create in 22:41 2017/12/23 0023
 */
@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();

        UserService service = new UserService();
        User user = service.login(username,password);

        if (user!=null){
            session.setAttribute("user",user);
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }else {
            request.setAttribute("loginInfo","用户名或密码错误");
            request.getRequestDispatcher(request.getContextPath()+"/login.jsp").forward(request,response);
        }
    }
}
