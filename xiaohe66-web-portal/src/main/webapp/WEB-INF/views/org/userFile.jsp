<%--
  Created by IntelliJ IDEA.
  User: xh
  Date: 19-10-28 028
  Time: 22:48 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="/WEB-INF/views/common/init.jsp"></jsp:include>
    <link type="text/css" rel="stylesheet" href="/plugin/xh/css/xh-paging.css"/>
    <link type="text/css" rel="stylesheet" href="/web/org/css/userFile.css"/>
    <script type="text/javascript" src="/plugin/xh/js/xh-paging.js"></script>
    <script type="text/javascript" src="/web/common/js/spark-md5.min.js"></script>
    <script type="text/javascript" src="/plugin/xh/js/xh-upload.js"></script>
    <script>
        let max = ${iPage.pages};
        let num = ${iPage.current};
        let hasEdit = ${hasDelete};
        let hasDelete = ${hasDelete};
    </script>
    <script type="text/javascript" src="/web/org/js/userFile.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/top.jsp"></jsp:include>
<div class="c">
    <div class="c_u">
        <p class="fl">资源列表</p>
        <input id="search" placeholder="搜索">
        <a href="javascript:" class="btn" id="searchBtn">搜索</a>
        <a href="javascript:" class="btn fr" id="upload">上传</a>
    </div>
    <div class="c_c">
        <table id="file_tab" border="0" cellpadding="0" cellspacing="0">
            <colgroup>
                <col>
                <col width="200px">
                <col width="100px">
                <col width="100px">
                <col width="200px">
            </colgroup>
            <thead>
            <tr>
                <th>文件名</th>
                <th>上传日期</th>
                <th>大小</th>
                <th>上传者</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${iPage.records}" var="item">
                <tr id="${item.id}">
                    <td><span class="name">${item.fileName}</span><span class="extension">${item.extension}</span></td>
                    <td>${item.createTime}</td>
                    <td>${item.fileSize}</td>
                    <td>${item.createUserName}</td>
                    <td class="btnTd">
                        <a href="javascript:" class="down">下载</a>
                        <%--<shiro:hasPermission name="userFile:update">
                            <a href="javascript:" class="rename">重命名</a>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="userFile:delete">
                            <a href="javascript:" class="delete">删除</a>
                        </shiro:hasPermission>--%>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div id="paging"></div>
    </div>
</div>
<jsp:include page="/WEB-INF/views/common/foot.jsp"></jsp:include>
</body>
</html>
