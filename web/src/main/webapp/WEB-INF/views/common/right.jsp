<%--
  Created by IntelliJ IDEA.
  User: xh
  Date: 18-04-02 002
  Time: 23:11 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${title}</title>
    <link type="text/css" rel="stylesheet" href="/css/xh/xh-common.css"/>
    <link type="text/css" rel="stylesheet" href="/web/common/css/right.css"/>

    <script type="text/javascript" src="/js/jquery/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="/js/xh/xh-common.js"></script>
    <script type="text/javascript" src="/js/xh/mouse-anim.js"></script>
    <script type="text/javascript" src="/js/xh/xh-mask.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/top.jsp"></jsp:include>
<div class="c">
    <div class="l">
        <jsp:include page="/WEB-INF/views/${page}.jsp"></jsp:include>
    </div>
    <div class="r">
        <div class="module1">
            <div class="title">
                <c:if test="${empty usrDivTitle}">作者</c:if>
                <c:if test="${not empty usrDivTitle}">${usrDivTitle}</c:if>
            </div>
            <div class="body master">
                <img src="/org/usr/file/img/${usrDto.imgFileId}" class="head_img" alt="${usrDto.usrName}"
                     onclick="location.href = '/text/article/list/${usrDto.id}'">
                <p>${usrDto.usrName}</p>
                <p>${usrDto.signature}</p>
            </div>
        </div>
        <div class="module1">
            <div class="title">资源下载</div>
            <div class="body source">
                <a href="javascript:void(0)">intellij.zip</a>
                <a href="javascript:void(0)">tomcat.zip</a>
                <a href="javascript:void(0)">jdk.zip</a>
                <a href="javascript:void(0)">mysql.zip</a>
                <a href="javascript:void(0)">navicat.zip</a>
            </div>
        </div>
        <div class="module1">
            <div class="title">文章分类</div>
            <div class="body category">
            </div>
        </div>
    </div>
</div>
<div class="f"></div>
</body>
</html>
