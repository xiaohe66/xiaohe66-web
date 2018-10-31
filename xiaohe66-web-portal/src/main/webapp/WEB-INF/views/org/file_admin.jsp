<%--
  Created by IntelliJ IDEA.
  User: xh
  Date: 18-03-21 021
  Time: 11:57 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link type="text/css" rel="stylesheet" href="/plugin/xh/css/xh-paging.css"/>
<link type="text/css" rel="stylesheet" href="/web/org/css/file_admin.css"/>
<script type="text/javascript" src="/web/common/js/spark-md5.min.js"></script>
<script type="text/javascript" src="/plugin/xh/js/xh-paging.js"></script>
<script>
    var pages = ${pageInfo.pages};
    var pageNum = ${pageInfo.pageNum};
</script>
<script type="text/javascript" src="/web/org/js/file_admin.js"></script>
<div class="fr add">
    <input type="file">
    <a class="btn">上传</a>
</div>
<p>文件管理</p>
<div class="c_c">
    <table id="file_tab" border="0" cellpadding="0" cellspacing="0">
        <colgroup>
            <col>
            <col width="60px">
            <col width="130px">
            <col width="100px">
            <col width="60px">
            <col width="90px">
        </colgroup>
        <thead>
        <tr>
            <th>文件名</th>
            <th>类型</th>
            <th>文件大小</th>
            <th>上传时间</th>
            <th>上传完成</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pageInfo.list}" var="item">
            <tr id="${item.id}">
                <td><a class="name">${item.fileName}</a></td>
                <td>${item.extension}</td>
                <td>${item.fileSize}</td>
                <td>${item.createTime}</td>
                <td>${item.isFinish ? "是":"否"}</td>
                <td>
                    <a href="javascript:void(0);" class="rename">重命名</a>
                    <a href="javascript:void(0);" class="del">删除</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div id="paging"></div>