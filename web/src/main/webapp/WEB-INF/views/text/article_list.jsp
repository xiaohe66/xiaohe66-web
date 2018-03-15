<%--
  Created by IntelliJ IDEA.
  User: xiaohe
  Date: 17-11-12 012
  Time: 21:40 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>文章列表</title>

    <link rel="stylesheet" type="text/css" href="<c:url value="/css/xh/xh-common.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/web/text/css/article_list.css"/>">

    <script type="text/javascript" src="<c:url value="/js/jquery/jquery-3.1.1.min.js"/>"></script>
    <%--<script type="text/javascript" src="/js/editor/wangEditor.min.js"></script>--%>
    <script type="text/javascript" src="<c:url value="/js/xh/xh-common.js"/>"></script>
    <script type="text/javascript" src="/web/text/js/article_detail.js"></script>

</head>
<body>
<jsp:include page="/WEB-INF/views/common/top.jsp"></jsp:include>
<div class="c">
    <div class="left">
        <div class="item" onclick="location.href='/detail'">
            <p class="title">标题</p>
            <div class="fr">
                <img src="/icon/eye.png">
                <span>50234</span>
                <%--<img src="/icon/praise.png">
                <span>1023</span>
                <img src="/icon/comment.png">
                <span>304</span>--%>
            </div>
            <div class="desc">
                发布时间：
                <span>2018-03-13</span>
                系统分类：
                <span>技术文章</span>
                作者分类：
                <span>好文</span>
            </div>
            <p class="digest">
                简单的 HTML 表格由 table 元素以及一个或多个 tr、th 或 td 元素组成。tr 元素定义表格行，th 元素定义表头，td 元素定义表格单元。
                简单的 HTML 表格由 table 元素以及一个或多个 tr、th 或 td 元素组成。tr 元素定义表格行，th 元素定义表头，td 元素定义表格单元。
            </p>
        </div>
    </div>
    <div class="right">
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
