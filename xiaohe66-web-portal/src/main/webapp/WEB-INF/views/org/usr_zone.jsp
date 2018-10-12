<%--
  Created by IntelliJ IDEA.
  User: xh
  Date: 18-03-16 016
  Time: 15:34 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="/WEB-INF/views/common/init.jsp"></jsp:include>
    <link type="text/css" rel="stylesheet" href="/web/org/css/usr_zone.css"/>

</head>
<body>
<jsp:include page="/WEB-INF/views/common/top.jsp"></jsp:include>
<div class="c">
    <div class="l l2">
        <div class="menu border1">
            <a href="/org/usr/me">个人资料</a>
            <a href="/text/article/admin">文章管理</a>
            <a href="/org/usr/file/admin">文件管理</a>
            <a href="/text/category/index">分类管理</a>
        </div>
    </div>
    <div class="r r2 border1">
        <jsp:include page="/WEB-INF/views/${page}.jsp"></jsp:include>
    </div>
</div>
<jsp:include page="/WEB-INF/views/common/foot.jsp"></jsp:include>
</body>
</html>
