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
<link type="text/css" rel="stylesheet" href="/web/text/css/article_admin.css"/>
<script type="text/javascript" src="/plugin/xh/js/xh-paging.js"></script>
<script>
    var pages = ${pageInfo.pages};
    var pageNum = ${pageInfo.pageNum};
</script>
<script type="text/javascript" src="/web/text/js/article_admin.js"></script>
<div class="c_t">
    <ul class="tab">
        <li class="active">全部</li>
        <li secretLevel="0">公开</li>
        <li secretLevel="1">私密</li>
    </ul>
</div>
<div class="c_c">
    <table id="article_tab" border="0" cellpadding="0" cellspacing="0">
        <colgroup>
            <col>
            <col width="80px">
            <col width="200px">
            <col width="170px">
            <col width="100px">
        </colgroup>
        <thead>
        <tr>
            <th>文章标题</th>
            <th>系统分类</th>
            <th>作者分类</th>
            <th>发表日期</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pageInfo.list}" var="item">
            <tr id="${item.id}">
                <td><a class="name">${item.title}</a></td>
                <td>${item.sysCategoryName}</td>
                <td>${item.perCategoryNames}</td>
                <td>${item.createTime}</td>
                <td>
                    <a href="javascript:void(0);" class="editer">编辑</a>
                    <a href="javascript:void(0);" class="del">删除</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div id="paging"></div>