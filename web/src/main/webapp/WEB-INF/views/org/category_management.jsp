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
    <title>乱七八糟导航</title>
    <link type="text/css" rel="stylesheet" href="/css/xh/xh-common.css"/>
    <link type="text/css" rel="stylesheet" href="/css/xh/xh-paging.css"/>
    <link type="text/css" rel="stylesheet" href="/web/org/css/category_management.css"/>

    <script type="text/javascript" src="/js/jquery/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="/js/xh/xh-common.js"></script>
    <script type="text/javascript" src="/js/xh/mouse-anim.js"></script>
    <%--<script type="text/javascript" src="/js/xh/xh-paging.js"></script>--%>
    <%--<script type="text/javascript" src="/js/xh/mask.js"></script>--%>
    <script type="text/javascript" src="/web/org/js/category_management.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/top.jsp"></jsp:include>
<div class="c">
    <div class="l l2">
        <ul class="menu border1">
            <li class="active">分类</li>
            <li>文章</li>
            <li>文件</li>
        </ul>
    </div>
    <div class="r r2">
        <div class="fr add">
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
                <%--<thead>
                <tr>
                    <th>分类名</th>
                    <th>创建时间</th>
                    <th>操作</th>
                </tr>
                </thead>--%>
                <tbody>
                <tr categoryId="1">
                    <td class="name">java</td>
                    <td>2018-03-22 18:23:23</td>
                    <td>
                        <a href="javascript:void(0);" class="rename">重命名</a>
                        <a href="javascript:void(0);" class="del">删除</a>
                    </td>
                </tr>
                <tr categoryId="1">
                    <td class="name">mysql</td>
                    <td>2018-03-22 18:23:23</td>
                    <td>
                        <a href="javascript:void(0);" class="rename">重命名</a>
                        <a href="javascript:void(0);" class="del">删除</a>
                    </td>
                </tr>
                <tr categoryId="1">
                    <td class="name">spring</td>
                    <td>2018-03-22 18:23:23</td>
                    <td>
                        <a href="javascript:void(0);" class="rename">重命名</a>
                        <a href="javascript:void(0);" class="del">删除</a>
                    </td>
                </tr>
                <tr categoryId="1">
                    <td class="name">spring</td>
                    <td>2018-03-22 18:23:23</td>
                    <td>
                        <a href="javascript:void(0);" class="rename">重命名</a>
                        <a href="javascript:void(0);" class="del">删除</a>
                    </td>
                </tr>
                <tr categoryId="1">
                    <td class="name">spring</td>
                    <td>2018-03-22 18:23:23</td>
                    <td>
                        <a href="javascript:void(0);" class="rename">重命名</a>
                        <a href="javascript:void(0);" class="del">删除</a>
                    </td>
                </tr>
                <tr categoryId="1">
                    <td class="name">spring</td>
                    <td>2018-03-22 18:23:23</td>
                    <td>
                        <a href="javascript:void(0);" class="rename">重命名</a>
                        <a href="javascript:void(0);" class="del">删除</a>
                    </td>
                </tr>
                <tr categoryId="1">
                    <td class="name">spring</td>
                    <td>2018-03-22 18:23:23</td>
                    <td>
                        <a href="javascript:void(0);" class="rename">重命名</a>
                        <a href="javascript:void(0);" class="del">删除</a>
                    </td>
                </tr>
                <tr categoryId="1">
                    <td class="name">spring</td>
                    <td>2018-03-22 18:23:23</td>
                    <td>
                        <a href="javascript:void(0);" class="rename">重命名</a>
                        <a href="javascript:void(0);" class="del">删除</a>
                    </td>
                </tr>
                <tr categoryId="1">
                    <td class="name">spring</td>
                    <td>2018-03-22 18:23:23</td>
                    <td>
                        <a href="javascript:void(0);" class="rename">重命名</a>
                        <a href="javascript:void(0);" class="del">删除</a>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <%--<div id="paging"></div>--%>
        <%--<div class="item">123</div>
        <div class="item">123</div>
        <div class="item">123</div>
        <div class="item">123</div>--%>
    </div>
</div>
<div class="f"></div>
</body>
</html>
