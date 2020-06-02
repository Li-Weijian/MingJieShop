package com.mingjie.web.servlet;

import com.mingjie.domain.Category;
import com.mingjie.domain.Product;
import com.mingjie.service.AdminService;
import com.mingjie.utils.CommonsUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:Liweijian
 * @Description: 添加商品 -- 文件上传
 */
@WebServlet(name = "AdminAddProductServlet")
public class AdminAddProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String temp_path = this.getServletContext().getRealPath("temp");
        String upload_path = this.getServletContext().getRealPath("upload");

        try {
            //1.创建磁盘文件工厂
            DiskFileItemFactory factory = new DiskFileItemFactory(1024*1024,new File(temp_path));
            //2.创建文件上传核心类
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setHeaderEncoding("UTF-8");

            //3.获得FileItemList
            List<FileItem> list = upload.parseRequest(request);

            //4.创建一个map集合添加表单字段用于BeanUtil封装
            Map<String, Object> map = new HashMap<>();

            for (FileItem item : list){
                boolean field = item.isFormField();
                if (field){
                    //普通表单
                    String fieldName = item.getFieldName();
                    String string = item.getString("UTF-8");
                    map.put(fieldName,string);
                }else {
                    //文件上传表单
                    String filename = item.getName();
                    map.put("pimage","upload/"+filename);
                    InputStream in = item.getInputStream();
                    OutputStream out = new FileOutputStream(upload_path+"/"+filename);
                    IOUtils.copy(in,out);

                    in.close();
                    out.close();
                    item.delete();
                }
            }

            //map封装完毕
            Product product = new Product();
            BeanUtils.populate(product,map);

            /*封装未自动封装的字段*/
            product.setPid(CommonsUtils.getUUID());
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String s = format.format(date);
            Date newDate = format.parse(s);
            product.setPdate(newDate);
            product.setPflag(0);
            Category category = new Category();
            category.setCid(map.get("cid").toString());
            product.setCategory(category);

            //传递到service层
            AdminService service = new AdminService();
            service.addProduct(product);

            //转发
            request.getRequestDispatcher(request.getContextPath()+"/admin?method=showProductListAdmin").forward(request,response);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
