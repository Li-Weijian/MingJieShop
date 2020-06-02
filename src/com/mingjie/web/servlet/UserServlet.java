package com.mingjie.web.servlet;

import com.mingjie.domain.User;
import com.mingjie.service.UserService;
import com.mingjie.utils.CommonsUtils;
import com.mingjie.utils.MailUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @Author:Liweijian
 * @Description: 用户相关功能的集成模块
 */
@WebServlet(name = "UserServlet")
public class UserServlet extends BaseServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    //用户激活
    public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String activeCode = request.getParameter("activeCode");
        UserService service = new UserService();
        service.active(activeCode);
        response.sendRedirect(request.getContextPath()+"/lwj-login.jsp");
    }

    //检查用户名是否存在
    public void checkUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        UserService service = new UserService();
        boolean isExiets = service.checkUsername(username);
        response.getWriter().write("{\"isExiets\":"+isExiets+"}");
    }

    //登录
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
                response.sendRedirect(request.getContextPath()+"/lwj-default.jsp");
            }else {
                request.setAttribute("loginInfo","用户名或密码错误");
                request.getRequestDispatcher(request.getContextPath()+"/lwj-login.jsp").forward(request,response);
            }
        }else {
            request.setAttribute("loginInfo","验证码错误");
            request.getRequestDispatcher(request.getContextPath()+"/lwj-login.jsp").forward(request,response);
        }
    }

    //用户登出
    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        //覆盖Cookie
        Cookie cookie_username = new Cookie("cookie_username","");
        Cookie cookie_password = new Cookie("cookie_password", "");
        //设置持久化时间为立即过期
        cookie_username.setMaxAge(0);
        cookie_password.setMaxAge(0);
        response.addCookie(cookie_username);
        response.addCookie(cookie_password);
        session.setAttribute("user",null);
        response.sendRedirect(request.getContextPath() + "/lwj-login.jsp");
    }

    //注册
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String checkCode = request.getParameter("checkCode");
        String checkcode_session = (String) request.getSession().getAttribute("checkcode_session");

        //校验验证码
        if (CommonsUtils.checkCode(checkCode,checkcode_session)){
            //封装User
            Map<String, String[]> map = request.getParameterMap();
            User user = new User();

            try {

                //自定义封装转换规则
            /*
            * 因为从客户端获取的参数都是字符串，但是BeanUtil没有将字符串转换成Date的规则，
            * 因此需要自己定义一个转换规则
            * */
                ConvertUtils.register(new Converter() {
                    @Override
                    public Object convert(Class aClass, Object value) {
                        //将String 转换成Date
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        Date parse = null;
                        try {
                            parse = format.parse(value.toString());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        return parse;
                    }
                }, Date.class);

                BeanUtils.populate(user,map);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            user.setUid(CommonsUtils.getUUID());
            user.setTelephone(null);
//      private int state;      //是否激活
            user.setState(1);
//      private String code;   //激活码
            String activeCode = CommonsUtils.getUUID();
            user.setCode(activeCode);


            //将User传递到service层
            UserService service = new UserService();
            boolean isSuccess = service.register(user);

            if (isSuccess){
                //发送激活邮件
               /* String emailMsg = "恭喜您注册成功，请点击下面的连接进行激活账户"
                        + "<a href='http://localhost:8080/user?method=active&activeCode="+activeCode+"'>"
                        + "http://localhost:8080/user?method=active&activeCode="+activeCode+"</a>";
                try {
                    MailUtils.sendMail(user.getEmail(),emailMsg);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }*/

                //重定向到激活页面
                response.sendRedirect(request.getContextPath()+"/lwj-login.jsp");
            }else {
                response.sendRedirect(request.getContextPath()+"/lwj-registerFail.jsp");
            }
        }else{
            request.setAttribute("checkResult","验证码不正确");
            request.getRequestDispatcher(request.getContextPath()+"/lwj-register.jsp").forward(request,response);
        }


    }

}
