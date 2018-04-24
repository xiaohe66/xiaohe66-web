<%--
  Created by IntelliJ IDEA.
  User: xiaohe
  Date: 17-10-29 029
  Time: 9:26 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link type="text/css" rel="stylesheet" href="/web/org/css/category_management.css"/>
<script type="text/javascript" src="/web/org/js/category_management.js"></script>
<div class="fr add" <c:if test="${size>=10}">style="display: none"</c:if> >
    <input placeholder="添加新分类">
    <a class="btn">添加</a>
</div>
<p>分类管理</p>
<div class="c_c">
    <table id="category_tab" border="0"cellpadding="0" cellspacing="0">
        <colgroup>
            <col>
            <col width="200px">
            <col width="100px">
        </colgroup>
        <thead>
        <tr>
            <th>分类名</th>
            <th>创建时间</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list}" var="item">
            <tr categoryId="${item.id}">
                <td class="name">${item.categoryName}</td>
                <td>${item.createTime}</td>
                <td>
                    <a class="rename">重命名</a>
                    <a class="del">删除</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
