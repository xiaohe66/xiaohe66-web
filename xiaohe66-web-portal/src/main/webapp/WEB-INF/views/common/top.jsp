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
<script type="text/javascript" src="/web/common/js/top.js"></script>
<%--<div id="login_body">
    <ul>
        <li>
            <span>账号</span>
            <input id="loginName" placeholder="用户名/邮箱地址">
            <div class="go_register">
                <a href="/org/usr/register" target="_blank">注册账号</a>
            </div>
        </li>
        <li>
            <span>密码</span>
            <input id="usrPwd" type="password" placeholder="密码">
            <div class="find_pwd">
                <a href="/org/usr/pwd" target="_blank">找回密码</a>
            </div>
        </li>
        <li  class="verify">
            <span>验证码</span>
            <input id="authCode" placeholder="验证码">
            <img src="">
        </li>
        <li class="login">
            <a class="btn">登录</a>
        </li>
    </ul>
    <p></p>
</div>--%>
<div class="t">
    <div class="t_t">
        <shiro:guest>
            <a href="/web/org/user/login.html">登录</a>
            <a href="/web/org/user/register.html">注册</a>
        </shiro:guest>
        <shiro:hasRole name="admin">
            <a href="/admin/index.html">后台管理</a>
        </shiro:hasRole>
        <shiro:authenticated>
            <a <%--href="/org/usr/me" --%>class="loginName">${usr.userName}</a>
            <%--<a href="/text/article/add">写文章</a>--%>
            <a href="javascript:logout();">注销</a>
        </shiro:authenticated>
    </div>
    <div class="t_c">
        <div class="tab">
            <%--<a href="/text/article/all">文章</a>--%>
            <a href="/web/game/game.jsp">游戏</a>
            <a href="/org/user/file/index">资源</a>
            <a href="/text/messageBoard/index">留言</a>
            <a href="/about">关于</a>
        </div>
        <p class="logo"><a href="/">xiaohe66</a></p>
        <%--<div class="search">
            <select class="search_type">
                <option value="https://www.baidu.com/s?wd=">百度</option>
                <option value="https://www.google.com/search?q=">谷歌</option>
            </select>
            <input class="inp" placeholder="搜索">
        </div>--%>
    </div>
</div>