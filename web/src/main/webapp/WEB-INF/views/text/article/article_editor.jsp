<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: xiaohe
  Date: 17-11-12 012
  Time: 0:10 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑</title>

    <link rel="stylesheet" type="text/css" href="<c:url value="/css/xh_common.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/web/text/article/css/article_editor.css"/>">

    <script type="text/javascript" src="<c:url value="/js/jquery-3.1.1.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/wangEditor.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/xhUtils.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/web/text/article/js/article_editor.js"/>"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/top.jsp"></jsp:include>
<div class="c">
    <c:if test="${not empty article}">
        <input id="id" type="hidden" value="${article.id}">
    </c:if>
    <div>
        <label>
            <input class="title" <c:if test="${not empty article}">value="${article.title}"</c:if>/>
        </label>
    </div>
    <div class="tool"></div>
    <div class="editor">
        <c:if test="${not empty article}"><p>${article.text}</p></c:if>
    </div>
    <%--<div class="option">
        <ul>
            <li>
                <label>分类</label>
                <select title="分类">
                    <c:forEach var="item" items="${categoryList}">
                        <option <c:if test="${not empty article && article.createdId ==item.id}">selected="selected"</c:if> value="${item.id}">${item.cName}</option>
                    </c:forEach>
                </select>
            </li>
            &lt;%&ndash;<li>
                <label>是否公开</label>
                <select title="私密">
                    <option>公开</option>
                    <option>私密</option>
                </select>
            </li>&ndash;%&gt;
        </ul>
    </div>--%>
    <div class="btn_d">
        <a class="btn" href="javascript:publish();">发表</a>
        <a class="btn">保存</a>
    </div>
</div>
<div class="f"></div>
</body>
</html>
