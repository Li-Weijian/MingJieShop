package com.mingjie.web.filter;

import com.mingjie.domain.User;
import com.mingjie.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @Author:Liweijian
 * @Description: 自动登录过滤器
 * @Date:Create in 23:49 2017/12/23 0023
 */
@WebFilter(filterName = "AutoLoginFilter")
public class AutoLoginFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpServletResponse httpResp = (HttpServletResponse) resp;
        HttpSession session = httpReq.getSession();
        String username = null;
        String password = null;
        httpReq.setCharacterEncoding("UTF-8");

        //获取Cookies
        Cookie[] cookies = httpReq.getCookies();

        //获取username和password的Cookie
        if (cookies!= null){
            for (Cookie cookie : cookies){
                if (cookie.getName().equals("cookie_username")){
                    username = cookie.getValue();
                    //恢复中文用户名
                    username = URLEncoder.encode(username,"UTF-8");
                }
                if (cookie.getName().equals("cookie_password")){
                    password = cookie.getValue();
                }
            }
        }

        //如果Cookie存在，则去数据库查询
        if (username != null && password != null){
            UserService service = new UserService();
            User user = service.login(username, password);
            session.setAttribute("user",user);
        }
        chain.doFilter(req, resp);
    }

    public void destroy() {
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
