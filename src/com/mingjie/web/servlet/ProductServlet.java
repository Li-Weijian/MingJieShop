package com.mingjie.web.servlet;

import com.google.gson.Gson;
import com.mingjie.domain.*;
import com.mingjie.service.CategoryListService;
import com.mingjie.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

/**
 * @Author:Liweijian
 * @Description: 商品相关功能的集成
 * @Date:Create in 16:46 2017/12/25 0025
 */
@WebServlet(name = "ProductServlet")
public class ProductServlet extends BaseServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       /* String methodName = request.getParameter("method");
        if ("productByCid".equals(methodName)){
            productByCid(request,response);
        }else if ("productInfo".equals(methodName)){
            productInfo(request,response);
        }else if ("categoryList".equals(methodName)){
            categoryList(request,response);
        }else if ("index".equals(methodName)){
            index(request,response);
        }*/


    }


    //根据cid查询商品
    public void productByCid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");
        String currentPageStr = request.getParameter("currentPage");
        if (currentPageStr == null) currentPageStr = "1"; //如果为空，则默认第一页
        int currentPage = Integer.parseInt(currentPageStr);
        int currentCount = 12;

        ProductService service = new ProductService();
        PageBean<Product> pageBean = service.findProductByCid(cid,currentPage,currentCount);

        //定义一个记录历史商品的集合类
        List<Product> historyProductList = new ArrayList<>();

        //获取Cookies  -- 将Cookies存入集合再添加到request域
        Cookie[] cookies = request.getCookies();
        if (cookies!=null){
            for (Cookie cookie: cookies){
                if ("pids".equals(cookie.getName())){
                    String pids = cookie.getValue(); //3-2-1
                    String[] split = pids.split("-");   //3，2，1
                    //去数据库查询对应的商品
                    for (int i = 0; i < split.length; i++){
                        Product pro = service.findProductByPid(split[i]);
                        historyProductList.add(pro);  //添加到集合中
                    }
                }
            }
        }

        request.setAttribute("historyProductList",historyProductList);      //将历史记录集合放到域中
        request.setAttribute("cid",cid);
        request.setAttribute("pageBean",pageBean);
        request.getRequestDispatcher(request.getContextPath() + "/product_list.jsp").forward(request,response);

    }

    //商品详情
    public void productInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pid = request.getParameter("pid");
        String cid = request.getParameter("cid");
        String currentPage = request.getParameter("currentPage");

        ProductService service = new ProductService();
        Product product = service.findProductByPid(pid);

        //获得客户端携带的Cookie -- 获得名字为pids的Cookie
        String pids = pid;
        Cookie[] cookies = request.getCookies();
        if (cookies!=null){
            for (Cookie cookie : cookies){
                if ("pids".equals(cookie.getName())){
                    pids = cookie.getValue();//2-1-3
                    //1-3-2  本次访问的pid为8： 8-1-3-2
                    //1-3-2  本次访问的pid为2： 2-1-3
                    String[] split = pids.split("-"); //切割：2,1,3
                    List<String> asList = Arrays.asList(split); //转换为集合
                    LinkedList<String> list = new LinkedList<>(asList); //转换为链表集合

                    //如果访问的pid已存在--先删除后添加到头部
                    if (list.contains(pid)){
                        list.remove(pid);
                        list.addFirst(pid);
                    }else {
                        //否则直接添加到头部
                        list.addFirst(pid);
                    }

                    //将3，1，2转换成3-1-2
                    StringBuffer sb = new StringBuffer();
                    for (int i = 0; i < list.size()&&i<7; i++){
                        sb.append(list.get(i));
                        sb.append("-"); // 3-1-2-
                    }
                    //去掉最后的-
                    pids = sb.substring(0, sb.length() - 1);
                }
            }
        }

        Cookie cookie_pids = new Cookie("pids",pids);
        response.addCookie(cookie_pids);


        request.setAttribute("cid",cid);
        request.setAttribute("currentPage",currentPage);
        request.setAttribute("product",product);
        request.getRequestDispatcher(request.getContextPath() + "/product_info.jsp").forward(request,response);
    }

    //商品类别列表
    public void categoryList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoryListService service = new CategoryListService();
        List<Category> categoryList = service.findAllCategory();
        Gson gson = new Gson();
        String json = gson.toJson(categoryList);
        response.setContentType("text/html;charset=utf8");
        response.getWriter().write(json);
    }

    //首页
    public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductService service = new ProductService();
        List<Product> hotProductList = service.findHotProduct();
        List<Product> newProductList = service.findNewProduct();

        request.setAttribute("newProductList",newProductList);
        request.setAttribute("hotProductList", hotProductList);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    //添加商品到购物车
    public void addProductToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //获取商品pid和购买的数量
        String pid = request.getParameter("pid");
        String buyNumStr = request.getParameter("buyNum");
        int buyNum = Integer.parseInt(buyNumStr);
        HttpSession session = request.getSession();

        //找到该商品
        ProductService service = new ProductService();
        Product product = service.findProductByPid(pid);

        CartItem cartItem = new CartItem();
        Cart cart = (Cart) session.getAttribute("cart");

        //判断在session中是否存在购物车
        if (cart == null){
            cart = new Cart();
        }

        //将购物信息加入购物项
        cartItem.setProduct(product);
        cartItem.setNum(buyNum);

        //计算小计
        double subTotal = buyNum * product.getMarket_price();
        cartItem.setSubTotal(subTotal);

        //将购物项加入购物车
        Map<String, CartItem> cartItemMap = cart.getCartItem();
        CartItem oldCartItem;
        double newSubTotal = 0.0;

        if (cartItemMap.containsKey(pid)){
            //如果该商品已在购物车
            oldCartItem = cartItemMap.get(pid);

            //修改数量
            int oldNum = oldCartItem.getNum();
            int newNum = oldNum + buyNum;
            oldCartItem.setNum(newNum);

            //修改小计
            double oldSubTotal = oldCartItem.getSubTotal();
            newSubTotal = buyNum * product.getMarket_price();
            oldCartItem.setSubTotal(oldSubTotal+newSubTotal);


//            oldCartItem.setNum(buyNum + cartItem.getNum());
//            oldCartItem.setSubTotal(subTotal + cartItem.getSubTotal());
        }else {
            //该商品不在购物车
            cartItem.setNum(buyNum);
            cartItem.setSubTotal(subTotal);
            cartItemMap.put(pid,cartItem);
        }
        cart.setCartItem(cartItemMap);

        //计算总费用
        double total = subTotal + cart.getTotal();
        cart.setTotal(total);

        //添加到session
        session.setAttribute("cart",cart);
        response.sendRedirect(request.getContextPath()+"/cart.jsp");
    }

    //删除购物车中的单件商品
    public void delCartItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        String pid = request.getParameter("pid");
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        Map<String, CartItem> cartItem = cart.getCartItem();

        //找到该商品并删除
        if (cartItem.containsKey(pid)){
            CartItem removeCartItem = cartItem.remove(pid);
            double subTotal = removeCartItem.getSubTotal();
            System.out.println(subTotal);
            //修改购物车总金额
            cart.setTotal(cart.getTotal() - subTotal);
            session.setAttribute("cart",cart);
        }

        request.getRequestDispatcher(request.getContextPath()+"/cart.jsp").forward(request,response);

    }

}
