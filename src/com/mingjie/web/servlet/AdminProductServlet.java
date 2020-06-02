package com.mingjie.web.servlet;

import com.mingjie.domain.Category;
import com.mingjie.service.CategoryListService;
import com.mingjie.service.ProductService;
import com.mingjie.utils.CommonsUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

/**
 * @Author:Liweijian
 * @Description:
 */
@WebServlet(name = "AdminProductServlet")
public class AdminProductServlet extends BaseServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String pid = request.getParameter("pid");
        ProductService service = new ProductService();
        service.deleteProductById(pid);

        request.getRequestDispatcher(request.getContextPath() + "/admin?method=showProductListAdmin").forward(request,response);



    }



}
