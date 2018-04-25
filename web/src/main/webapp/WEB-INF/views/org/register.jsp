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
    <title>注册</title>
    <jsp:include page="/WEB-INF/views/common/init.jsp"></jsp:include>
    <link type="text/css" rel="stylesheet" href="/web/org/usr/css/register.css">
    <script type="text/javascript" src="/web/org/usr/js/register.js"></script>
</head>
<body>
<div class="t">
    <div class="t_t">
    </div>
    <div class="t_c">
        <div class="tab">
            <a>文章</a>
            <a href="/text/messageBoard/index">留言</a>
            <a href="/about">关于</a>
        </div>
        <p class="logo"><a href="/">xiaohe66</a></p>
        <div class="search">
            <select class="search_type">
                <option value="https://www.baidu.com/s?wd=">百度</option>
                <option value="https://www.google.com/search?q=">谷歌</option>
            </select>
            <input class="inp" placeholder="搜索">
        </div>
    </div>
</div>
<div class="c">
    <div class="c_c border1">
        <p>欢迎注册</p>
        <ul>
            <li id="usrName">
                <div><label>*</label>用户名:</div><input placeholder="请输入用户名">
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
<div class="f"></div>
</body>
</html>
