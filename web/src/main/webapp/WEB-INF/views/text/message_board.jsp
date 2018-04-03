<%--
  Created by IntelliJ IDEA.
  User: xh
  Date: 18-03-15 015
  Time: 10:02 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<link type="text/css" rel="stylesheet" href="/css/xh/xh-paging.css"/>
<link type="text/css" rel="stylesheet" href="/web/text/css/message_board.css"/>
<script type="text/javascript" src="/js/xh/xh-paging.js"></script>
<script type="text/javascript" src="/web/text/js/message_board.js"></script>
<%-- 留言 --%>
<input type="hidden" id="usrId" value="${usr.id}">
<input type="hidden" id="size" value="${size}">
<shiro:guest>
<div class="msg border1">
    <p class="title">发表留言</p>
    <p class="hint"><a href="javascript:showLogin();">登录</a>后才能留言</p>
</div>
</shiro:guest>
<shiro:authenticated>
<div class="msg border1">
    <p class="title">发表留言</p>
    <div class="edit">
        <textarea></textarea>
    </div>
    <a class="btn">发表留言</a>
</div>
</shiro:authenticated>
<div class="content">
    <c:forEach items="${list}" var="item" end="5" varStatus="statu">
        <div class="item">
            <div>
                <div class="fl">
                    <img class="head_img" src="" alt="头像">
                </div>
                <div class="u_r">
                    <div class="u_r_d">
                        <span>${item.usrName}</span>
                        <span>${item.createTime}</span>
                        <span>${item.id}楼</span>
                    </div>
                    <div class="desc">${item.msg}</div>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
<div id="paging"></div>