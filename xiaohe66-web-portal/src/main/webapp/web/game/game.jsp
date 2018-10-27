<%--
  Created by IntelliJ IDEA.
  User: xh
  Date: 18-10-27 027
  Time: 14:36 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        request.setAttribute("title","游戏");
    %>
    <jsp:include page="/WEB-INF/views/common/init.jsp"></jsp:include>
    <style>
        .c_c{
            font-size: 50px;
            text-align: center;
            margin-top: 100px;
        }
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/top.jsp"></jsp:include>
<div class="c">
    <div class="c_c">开发中...</div>
</div>
<jsp:include page="/WEB-INF/views/common/foot.jsp"></jsp:include>
</body>
</html>
