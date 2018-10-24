<%--
  Created by IntelliJ IDEA.
  User: xh
  Date: 18-10-24 024
  Time: 12:21 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/views/common/init.jsp"></jsp:include>
    <link rel="stylesheet" type="text/css" href="/web/org/css/find_pwd.css">

    <script type="text/javascript" src="/js/xh/xh-validator.js"></script>
    <script type="text/javascript" src="/web/org/js/find_pwd.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/top.jsp"></jsp:include>
<div class="c" id="find_pwd">
    <div class="c_c border1">
        <p class="title">修改密码</p>
        <ul>
            <li id="email">
                <div><label>*</label>邮箱:</div><input placeholder="请输入邮箱">
                <span></span>
            </li>
            <li id="code" class="s1">
                <div><label>*</label>验证码:</div><input placeholder="请输入验证码">
                <img src="/authCode/img">
                <span></span>
            </li>
            <li id="password" class="s2">
                <div><label>*</label>新密码:</div><input placeholder="请输入密码" type="password">
                <span></span>
            </li>
            <li id="password2" class="s2">
                <div><label>*</label>重复密码:</div><input placeholder="重复输入密码" type="password">
                <span></span>
            </li>
            <li id="emailCode" class="s2">
                <div><label>*</label>验证码:</div><input placeholder="请输入邮箱中的验证码">
                <span></span>
            </li>
            <li id="btn">
                <a class="btn" id="next">下一步</a>
                <a class="btn" id="prev">上一步</a>
                <a class="btn" id="submit">提交</a>
            </li>
        </ul>
    </div>
</div>
<jsp:include page="/WEB-INF/views/common/foot.jsp"></jsp:include>
</body>
</html>
