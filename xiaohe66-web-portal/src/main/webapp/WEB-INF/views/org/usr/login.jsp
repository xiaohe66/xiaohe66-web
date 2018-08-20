<%--
  Created by IntelliJ IDEA.
  User: xiaohe
  Date: 17-11-12 012
  Time: 0:16 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/xh_common.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/web/org/usr/css/login.css"/>">

    <script type="text/javascript" src="<c:url value="/js/jquery-3.1.1.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/xhUtils.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/web/org/usr/js/login.js"/>"></script>
</head>
<body>
<%--<jsp:include page="/WEB-INF/views/common/top.jsp"></jsp:include>--%>
    <div class="content">
        <p>
            <label>账号：</label>
            <input type="text" id="usrName">
        </p>
        <p>
            <label>密码：</label>
            <input type="password" id="usrPwd">
        </p>
        <p>
            <button type="button" id="loginBtn">登录</button>
            <button type="button">取消</button>
        </p>
    </div>
</body>
</html>
