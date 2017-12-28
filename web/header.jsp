<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<script type="text/javascript">
    $(function () {
        var content = "";
        $.post(
            "${pageContext.request.contextPath}/product?method=categoryList",
            function (data) {
                for (var i = 0; i < data.length; i++) {
                    content += "<li><a href='${pageContext.request.contextPath}/product?method=productByCid&cid="+data[i].cid+"'>" + data[i].cname + "</a></li>";
                }
                $("#category").html(content);
            },
            "json"
        );
    });
</script>
<!-- 登录 注册 购物车... -->
<div class="container-fluid">
    <div class="col-md-4">
        <img src="img/logo4.png"/>
    </div>
    <div class="col-md-5">
        <img src="img/header.png"/>
    </div>
    <div class="col-md-3" style="padding-top:20px">
        <ol class="list-inline">
            <c:if test="${empty user}">
                <li><a href="login.jsp">登录</a></li>
                <li><a href="register.jsp">注册</a></li>
            </c:if>
            <c:if test="${!empty user}">
                <li><a href="#">${user.username},欢迎您</a></li>
                <li><a href="${pageContext.request.contextPath}/user?method=logout">退出</a></li>
            </c:if>

            <li><a href="cart.jsp">购物车</a></li>
            <li><a href="order_list.jsp">我的订单</a></li>
        </ol>
    </div>
</div>

<!-- 导航条 -->
<div class="container-fluid">
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="${pageContext.request.contextPath}/default.jsp">首页</a>
            </div>

            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav" id="category"></ul>
                <form class="navbar-form navbar-right" role="search">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Search">
                    </div>
                    <button type="submit" class="btn btn-default">Submit</button>
                </form>
            </div>
        </div>
    </nav>
</div>