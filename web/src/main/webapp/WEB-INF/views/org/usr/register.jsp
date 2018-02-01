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
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/xh_common.css"/>">
    <link type="text/css" rel="stylesheet" href="<c:url value="/web/org/usr/css/register.css"/>">

    <script type="text/javascript" src="<c:url value="/js/jquery-3.1.1.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/xhUtils.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/web/org/usr/js/register.js"/>"></script>
</head>
<body>
    <div class="content">
        <table class="register_tab">
            <tbody>
            <tr>
                <td>账号</td>
                <td><input id="usrName"></td>
                <td></td>
            </tr>
            <tr>
                <td>密码</td>
                <td><input type="password" id="usrPwd"></td>
                <td></td>
            </tr>
            <tr>
                <td>重复</td>
                <td><input type="password" id="usrPwd2"></td>
                <td></td>
            </tr>
            <tr>
                <td>验证码</td>
                <td><input id="code"></td>
                <td><img src="/validate" id="codeImg"></td>
            </tr>
            <tr>
                <td></td>
                <td><a class="btn" href="javascript:register();">注册</a></td>
                <%--<a class="btn">注册</a>--%>
                <td></td>
            </tr>
            </tbody>
        </table>
    </div>
</body>
</html>
