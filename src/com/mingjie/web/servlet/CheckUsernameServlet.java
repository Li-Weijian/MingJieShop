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
 * @Description: 校验用户是否存在
 * @Date:Create in 20:02 2017/12/23 0023
 */
@WebServlet(name = "CheckUsernameServlet")
public class CheckUsernameServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        UserService service = new UserService();
        boolean isExiets = service.checkUsername(username);

        response.getWriter().write("{\"isExiets\":"+isExiets+"}");
    }
}
