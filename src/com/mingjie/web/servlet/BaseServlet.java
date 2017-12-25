package com.mingjie.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author:Liweijian
 * @Description: 基础类 -- 模块的父类
 * @Date:Create in 17:04 2017/12/25 0025
 */


/**
 * 说明：如果需要使用此类，必须在对应模块的Servlet继承此类
 * */

@WebServlet(name = "BaseServlet")
public class BaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        try {
            //1.获得传递参数
            String methodName = req.getParameter("method");

            //2.通过反射获得类的字节码对象
            Class clazz = this.getClass();   //this代表哪个被访问，就是哪个

            //3.获得对应请求参数的方法
            Method method = clazz.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);

            //4.执行相应的方法
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }
}
