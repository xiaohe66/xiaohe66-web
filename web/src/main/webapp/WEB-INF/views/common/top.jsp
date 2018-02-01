<%--
  Created by IntelliJ IDEA.
  User: xiaohe
  Date: 17-11-12 012
  Time: 0:13 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="t">
    <script type="text/javascript" src="<c:url value="/web/common/js/top.js"/>"></script>
    <a href="/index" class="home">首页</a>
    <shiro:guest>
        <a href="/org/usr/index">登录</a>
        |
        <a href="/org/usr/register">注册</a>
    </shiro:guest>
    <shiro:authenticated>
        <a class="usrName" style="margin-right:20px;">${usr.usrName}</a>
        <a href="/text/article/add">写文章</a>
        |
        <a href="javascript:logout();">注销</a>
    </shiro:authenticated>
</div>
