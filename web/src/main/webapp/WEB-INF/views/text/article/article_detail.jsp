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

    <link rel="stylesheet" type="text/css" href="<c:url value="/css/xh_common.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/web/text/article/css/article_detail.css"/>">

    <script type="text/javascript" src="<c:url value="/js/jquery-3.1.1.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/xhUtils.js"/>"></script>

</head>
<body>
<jsp:include page="/WEB-INF/views/common/top.jsp"></jsp:include>
<div class="c">
    <div>
        <label class="title">${article.title}</label>
        <c:if test="${usr.id == article.createdId}">
            <a class="editor" href="/text/article/editor?id=${article.id}">编辑</a>
        </c:if>
    </div>
    <div>
        <label class="date">${article.updatedTime}</label>
        <label class="author">${article.createdId}</label>
        <%--<label>生活</label>--%>
    </div>
    <div class="text">${article.text}</div>
</div>
<div class="f"></div>
</body>
</html>
