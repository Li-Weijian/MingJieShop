package com.mingjie.web.servlet;

import com.google.gson.Gson;
import com.mingjie.domain.Category;
import com.mingjie.domain.Order;
import com.mingjie.domain.Product;
import com.mingjie.service.AdminService;
import com.mingjie.service.CategoryListService;
import com.mingjie.utils.CommonsUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @Author:Liweijian
 * @Description:
 */
@WebServlet(name = "AdminCategoryServlet")
public class AdminCategoryServlet extends BaseServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    //添加类别
    public void addCategory(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, String[]> parameterMap = request.getParameterMap();
        Category category = new Category();
        BeanUtils.populate(category,parameterMap);
        category.setCid(CommonsUtils.getUUID());
        CategoryListService service = new CategoryListService();
        service.addCategory(category);

        request.getRequestDispatcher(request.getContextPath() + "/admin?method=showAllCategory").forward(request,response);
    }

    //编辑分类
    public void editCategory(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, InvocationTargetException, IllegalAccessException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        Category category = new Category();
        BeanUtils.populate(category, parameterMap);
        CategoryListService service = new CategoryListService();
        service.updateCategoryById(category);

        request.getRequestDispatcher(request.getContextPath() + "/admin?method=showAllCategory").forward(request,response);
    }

    public void toEdit(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
        String cid = request.getParameter("cid");
        CategoryListService service = new CategoryListService();
        Category category = service.findCategoryById(cid);

        request.setAttribute("category",category);
        request.getRequestDispatcher("/admin/category/lwj-edit.jsp").forward(request,response);
    }

    //删除分类
    public void deleteCategory(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
        String cid = request.getParameter("cid");
        CategoryListService service = new CategoryListService();
        service.deleteCategoryById(cid);

        request.getRequestDispatcher(request.getContextPath() + "/admin?method=showAllCategory").forward(request,response);

    }
}
