<%--
  Created by IntelliJ IDEA.
  User: xiaohe
  Date: 17-10-29 029
  Time: 9:26 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/xh_common.css"/>">
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/index.css"/>">

    <script type="text/javascript" src="<c:url value="/js/jquery-3.1.1.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/xhUtils.js"/>"></script>

</head>
<body>
<jsp:include page="/WEB-INF/views/common/top.jsp"></jsp:include>
<div class="c">
    <div class="top">
        <img src="/image/banner.png">
    </div>
    <div class="content">
        <div class="left">
            <ul>
                <%--<li>分类1</li>
                <li>分类1</li>
                <li>分类1</li>
                <li>分类1</li>
                <li>分类1</li>
                <li>分类1</li>--%>
            </ul>
        </div>
        <div class="right">
            <div class="head">
                <input type="text">
                <%--<a>搜索</a>--%>
            </div>
            <div class="list">
                <ul>
                    <c:forEach items="${list}" var="item">
                        <li>
                            <p><a class="title" href="/text/article/detail?id=${item.id}">${item.title}</a></p>
                            <p>
                                <label class="date">${item.updatedTime}</label>
                                <label class="author">${item.createdId}</label>
                            </p>
                            <p class="text"><%--${item.text}--%></p>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</div>
<div class="f"></div>
</body>
</html>
