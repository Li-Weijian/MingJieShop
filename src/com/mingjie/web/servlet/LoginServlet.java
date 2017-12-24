package com.mingjie.web.servlet;

import com.mingjie.domain.User;
import com.mingjie.service.UserService;
import com.mingjie.utils.CommonsUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
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
        String autoLogin = request.getParameter("autoLogin");
        String checkCode = request.getParameter("checkCode");
        HttpSession session = request.getSession();
        User user = null;
        String checkcode_session = (String) session.getAttribute("checkcode_session");

        //校验验证码
        if (CommonsUtils.checkCode(checkCode,checkcode_session)){
            UserService service = new UserService();
            user = service.login(username,password);

            if (user!=null){
                //勾选自动登录
                if (autoLogin != null){
                    //创建Cookie
                    Cookie cookie_username = new Cookie("cookie_username",username);
                    Cookie cookie_password = new Cookie("cookie_password", password);
                    //设置持久化时间
                    cookie_username.setMaxAge(60*60);
                    cookie_password.setMaxAge(60*60);
                    //添加Cookie
                    response.addCookie(cookie_username);
                    response.addCookie(cookie_password);
                }
                session.setAttribute("user",user);
                response.sendRedirect(request.getContextPath()+"/index.jsp");
            }else {
                request.setAttribute("loginInfo","用户名或密码错误");
                request.getRequestDispatcher(request.getContextPath()+"/login.jsp").forward(request,response);
            }
        }else {
            request.setAttribute("loginInfo","验证码错误");
            request.getRequestDispatcher(request.getContextPath()+"/login.jsp").forward(request,response);
        }
    }
}
