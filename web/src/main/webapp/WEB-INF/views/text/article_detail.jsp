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
    <title>详情</title>

    <link rel="stylesheet" type="text/css" href="<c:url value="/css/xh/xh-common.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/editor/wangEditor.min.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/web/text/css/article_detail.css"/>">

    <script type="text/javascript" src="<c:url value="/js/jquery/jquery-3.1.1.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/xh/xh-common.js"/>"></script>
    <script type="text/javascript" src="/js/xh/mouse-anim.js"></script>
    <script type="text/javascript" src="/js/xh/xh-mask.js"></script>
    <script type="text/javascript" src="/web/text/js/article_detail.js"></script>

</head>
<body>
<jsp:include page="/WEB-INF/views/common/top.jsp"></jsp:include>
<div class="c">
    <div class="l border1">

        <div class="c_u">
            <p>${article.title}</p>
            <div class="fr">
                <img src="/icon/eye.png">
                <span>0</span>
                <%--<img src="/icon/praise.png">
                <span>1023</span>
                <img src="/icon/comment.png">
                <span>304</span>--%>
                <a href="/text/article/editor?id=${article.id}">编辑</a>
            </div>
            <div class="desc">
                <span>${article.createTime}</span>
                <span>${article.sysCategoryName}</span>
                <span>${article.perCategoryNames}</span>
            </div>
        </div>
        <div class="c_c editor">${article.text}</div>
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
