<%--
  Created by IntelliJ IDEA.
  User: 35264
  Date: 2017/12/24 0024
  Time: 21:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<%
    request.getRequestDispatcher("/product?method=index").forward(request,response);
%>

</body>
</html>
