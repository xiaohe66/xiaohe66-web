<%--
  Created by IntelliJ IDEA.
  User: xiaohe
  Date: 17-11-12 012
  Time: 21:40 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="/css/editor/wangEditor.min.css">
<link rel="stylesheet" type="text/css" href="/web/text/css/article_detail.css">
<script type="text/javascript" src="/web/text/js/article_detail.js"></script>
<div class="c_u">
    <p>${article.title}</p>
    <div class="fr">
        <img src="/icon/eye.png">
        <span>${article.count}</span>
        <%--<img src="/icon/praise.png">
        <span>1023</span>
        <img src="/icon/comment.png">
        <span>304</span>--%>
        <c:if test="${usr.id == article.createId}">
            <a href="/text/article/editor/${article.id}">编辑</a>
        </c:if>
    </div>
    <div class="desc">
        <span>${article.createTime}</span>
        <span>${article.sysCategoryName}</span>
        <span>${article.perCategoryNames}</span>
    </div>
</div>
<div class="c_c editor">${article.text}</div>
