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
<html>
<head>
    <title>${usr.usrName}的留言板</title>
    <link type="text/css" rel="stylesheet" href="/css/xh/xh-common.css"/>
    <link type="text/css" rel="stylesheet" href="/css/xh/xh-paging.css"/>
    <link type="text/css" rel="stylesheet" href="/web/text/css/message_board.css"/>

    <script type="text/javascript" src="/js/jquery/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="/js/editor/wangEditor.min.js"></script>
    <script type="text/javascript" src="/js/xh/xh-mask.js"></script>
    <script type="text/javascript" src="/js/xh/xh-common.js"></script>
    <script type="text/javascript" src="/js/xh/mouse-anim.js"></script>
    <script type="text/javascript" src="/js/xh/xh-paging.js"></script>
    <script type="text/javascript" src="/web/text/js/message_board.js"></script>
</head>
<body>
<%-- 留言 --%>
<input type="hidden" id="usrId" value="${usr.id}">
<input type="hidden" id="size" value="${size}">
<jsp:include page="/WEB-INF/views/common/top.jsp"></jsp:include>
<div class="c">
    <div class="l">
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
    </div>
    <div class="r">
        <div class="module1">
            <div class="title">作者</div>
            <div class="body master">
                <img src="" class="head_img" alt="站长">
                <p>小何</p>
                <p>一个梦想成为大佬的屌丝程序员</p>
            </div>
        </div>
        <div class="module1">
            <div class="title">作者分类</div>
            <div class="body category">
                <p><a href="javascript:void(0);">分类(20)</a></p>
                <p><a href="javascript:void(0);">分类(20)</a></p>
                <p><a href="javascript:void(0);">分类(20)</a></p>
                <p><a href="javascript:void(0);">分类(20)</a></p>
                <p><a href="javascript:void(0);">分类(20)</a></p>
                <p><a href="javascript:void(0);">分类(20)</a></p>
            </div>
        </div>
    </div>
</div>
<div class="f"></div>
</body>
</html>
