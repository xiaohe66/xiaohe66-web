<%--
  Created by IntelliJ IDEA.
  User: xh
  Date: 18-03-16 016
  Time: 15:34 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的</title>
    <link type="text/css" rel="stylesheet" href="/css/xh/xh-common.css"/>
    <link type="text/css" rel="stylesheet" href="/web/org/css/usr_zone.css"/>

    <script type="text/javascript" src="/js/jquery/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="/js/xh/xh-common.js"></script>
    <script type="text/javascript" src="/js/xh/mouse-anim.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/top.jsp"></jsp:include>
<div class="c">
    <div class="l l2">
        <div class="menu border1">
            <a href="/">个人资料</a>
            <a href="/">文章管理</a>
            <a href="/">文件管理</a>
            <a href="/">分类管理</a>
        </div>
    </div>
    <div class="r r2 border1">
        <jsp:include page="/WEB-INF/views/org/file_list.jsp"></jsp:include>
        <%--<jsp:include page="/WEB-INF/views/org/category_management.jsp"></jsp:include>--%>
        <%--<jsp:include page="/WEB-INF/views/org/usr_data.jsp"></jsp:include>--%>
    </div>
</div>
<div class="f"></div>
</body>
</html>
