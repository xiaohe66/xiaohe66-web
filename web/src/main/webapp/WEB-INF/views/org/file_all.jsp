<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: xh
  Date: 18-04-25 025
  Time: 09:14 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link type="text/css" rel="stylesheet" href="/web/org/css/file_all.css"/>
<link type="text/css" rel="stylesheet" href="/css/xh/xh-paging.css"/>
<script>
    var max = ${pageInfo.pages};
    var num = ${pageInfo.pageNum};
</script>
<script type="text/javascript" src="/js/xh/xh-paging.js"></script>
<script type="text/javascript" src="/web/org/js/file_all.js"></script>
<div class="c_u">
    <p class="fl">资源列表</p>
    <div>
        <a class="btn" id="onlyWebmaster">只看站长</a>
        <input id="search" placeholder="搜索">
    </div>
</div>
<div class="c_c">
    <table id="file_tab" border="0" cellpadding="0" cellspacing="0">
        <colgroup>
            <col>
            <col width="100px">
            <col width="200px">
            <col width="100px">
        </colgroup>
        <thead>
        <tr>
            <th>文件名</th>
            <th>文件类型</th>
            <th>上传日期</th>
            <th>上传者</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pageInfo.list}" var="item">
            <tr id="${item.id}">
                <td><a class="name">${item.fileName}</a></td>
                <td>${item.extension}</td>
                <td>${item.createTime}</td>
                <td><a href="/text/article/list/${item.createId}">${item.usrName}</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div id="paging"></div>
</div>
