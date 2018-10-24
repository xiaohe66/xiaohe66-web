<%--
  Created by IntelliJ IDEA.
  User: xh
  Date: 18-04-04 004
  Time: 16:42 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link type="text/css" rel="stylesheet" href="/web/org/css/usr_data.css"/>
<script type="text/javascript" src="/js/spark-md5.min.js"></script>
<script type="text/javascript" src="/web/org/js/usr_data.js"></script>
<p>个人资料</p>
<div class="right">
    <img src="/org/usr/file/img/${usrDto.imgFileId}" class="head_img">
    <div id="file">
        <input type="file">
        <a class="btn">更换头像</a>
    </div>
</div>
<div class="left">
    <ul>
        <li>
            <span>账号</span>
            <input value="${usrDto.usrName}" disabled="disabled">
        </li>
        <li>
            <span>邮箱</span>
            <input value="${usrDto.email}" disabled="disabled">
        </li>
        <li>
            <span>密码</span>
            <input value="******" type="password" disabled="disabled">
            <a href="/org/usr/pwd" target="_blank">修改密码</a>
        </li>
        <li>
            <span>签名</span>
            <textarea class="signature border1" placeholder="请输入签名">${usrDto.signature}</textarea>
        </li>
    </ul>
</div>
<div class="down">
    <a class="btn">保存修改</a>
</div>