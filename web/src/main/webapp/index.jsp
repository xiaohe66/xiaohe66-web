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
    <title>主页</title>
    <link type="text/css" rel="stylesheet" href="/css/xh/xh-common.css"/>
    <link type="text/css" rel="stylesheet" href="/css/index.css"/>

    <script type="text/javascript" src="/js/jquery/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="/js/xh/xh-common.js"></script>
    <script type="text/javascript" src="/js/xh/mouse-anim.js"></script>
    <script>
        $(function () {
            $(".btn").click(function () {
                var val = $(".inp").val();
                if(val === undefined || val === ""){
                    alert("请输入");
                    return;
                }
                window.open($(".search_type").val()+val);
            });
        });
    </script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/top.jsp"></jsp:include>
<div class="c">
    <%--<div class="c_c">
        <div class="d_search">
            <select class="search_type">
                <option value="https://www.baidu.com/s?wd=">百度</option>
                <option value="https://www.google.com/search?q=">谷歌</option>
            </select>
            <input class="inp" placeholder="请输入">
            <a href="javascript:void(0);" class="btn">搜索</a>
        </div>
        <div class="d_dynamic">
            <ul>
                <li class="">
                    <div class="title">发表了一篇文章</div>
                    <div class="desc">今天天气不错</div>
                </li>
            </ul>
        </div>
    </div>--%>
</div>
<div class="f">

</div>
</body>
</html>
