<%--
  Created by IntelliJ IDEA.
  User: xiaohe
  Date: 17-11-12 012
  Time: 18:02 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="/WEB-INF/views/common/init.jsp"></jsp:include>
    <link type="text/css" rel="stylesheet" href="/web/org/usr/css/register.css">
    <script type="text/javascript" src="/js/xh/xh-validator.js"></script>
    <script type="text/javascript" src="/web/org/usr/js/register.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/top.jsp"></jsp:include>
<div class="c">
    <div class="c_c border1">
        <p>欢迎注册</p>
        <ul>
            <li id="usrName">
                <div><label>*</label>用户名:</div><input placeholder="用户名(中、英、数字和下划线)">
                <span></span>
            </li>
            <li id="email">
                <div><label>*</label>邮箱:</div><input placeholder="请输入邮箱">
                <span></span>
            </li>
            <li id="password">
                <div><label>*</label>密码:</div><input placeholder="请输入密码" type="password">
                <span></span>
            </li>
            <li id="password2">
                <div><label>*</label>重复密码:</div><input placeholder="重复输入密码" type="password">
                <span></span>
            </li>
            <li id="code">
                <div><label>*</label>验证码:</div><input placeholder="请输入验证码">
                <img src="/authCode/img">
                <span></span>
            </li>
            <li id="signature">
                <div>个性签名:</div>
                <textarea class="border1" placeholder="请输入个性签名"></textarea>
                <span></span>
            </li>
            <li id="register">
                <a class="btn">注册</a>
            </li>
        </ul>
    </div>
</div>
<jsp:include page="/WEB-INF/views/common/foot.jsp"></jsp:include>
</body>
</html>
