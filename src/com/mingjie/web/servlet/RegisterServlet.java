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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @Author:Liweijian
 * @Description: 注册Servlet
 * @Date:Create in 16:34 2017/12/23 0023
 */
public class RegisterServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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

//      private String uid;
        user.setUid(CommonsUtils.getUUID());
//      private String telephone;
        user.setTelephone(null);
//      private int state;      //是否激活
        user.setState(0);
//      private String code;   //激活码
        String activeCode = CommonsUtils.getUUID();
        user.setCode(activeCode);


        //将User传递到service层
        UserService service = new UserService();
        boolean isSuccess = service.register(user);

        if (isSuccess){
            //发送激活邮件
            String emailMsg = "恭喜您注册成功，请点击下面的连接进行激活账户"
                    + "<a href='http://localhost:8080/active?activeCode="+activeCode+"'>"
                    + "http://localhost:8080/active?activeCode="+activeCode+"</a>";
            try {
                MailUtils.sendMail(user.getEmail(),emailMsg);
            } catch (MessagingException e) {
                e.printStackTrace();
            }

            //重定向到激活页面
            response.sendRedirect(request.getContextPath()+"/registerSuccess.jsp");
        }else {
            response.sendRedirect(request.getContextPath()+"/registerFail.jsp");
        }

    }
}
