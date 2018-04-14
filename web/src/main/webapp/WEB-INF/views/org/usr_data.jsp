<%--
  Created by IntelliJ IDEA.
  User: xh
  Date: 18-04-04 004
  Time: 16:42 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link type="text/css" rel="stylesheet" href="/web/org/css/usr_data.css"/>
<script type="text/javascript" src="/web/org/js/usr_data.js"></script>
<p>个人资料</p>
<div class="right">
    <img src="" class="head_img">
    <div>
        <a class="btn">更换头像</a>
    </div>
</div>
<div class="left">
    <ul>
        <li>
            <span>账号</span>
            <input value="xiaohe" disabled="disabled">
        </li>
        <%--<li>
            <span>邮箱</span>
            <input placeholder="请输入邮箱">
        </li>--%>
        <%--<li>
            <span>昵称</span>
            <input placeholder="请输入昵称">
        </li>--%>
        <li>
            <span>签名</span>
            <textarea class="signature border1" placeholder="请输入签名"></textarea>
        </li>
    </ul>
</div>
<div class="down">
    <a class="btn">保存修改</a>
</div>