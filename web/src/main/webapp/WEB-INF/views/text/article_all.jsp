<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: xh
  Date: 18-04-20 020
  Time: 11:19 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link type="text/css" rel="stylesheet" href="/web/text/css/article_all.css"/>
<link type="text/css" rel="stylesheet" href="/css/xh/xh-paging.css"/>
<script type="text/javascript" src="/js/xh/xh-paging.js"></script>
<script>
    var max = ${pageInfo.pages};
    var num = ${pageInfo.pageNum};
</script>
<script type="text/javascript" src="/web/text/js/article_all.js"></script>
<div class="c_u">
    <p class="fl">文章列表</p>
    <div>
        <a class="btn" id="onlyWebmaster">只看站长</a>
        <input id="search" placeholder="搜索">
    </div>
</div>
<div class="c_c">
    <c:forEach items="${pageInfo.list}" var="item">
        <div class="item" id="${item.id}">
            <div>
                <div class="fl">
                    <img class="head_img" src="/org/usr/file/img/${item.imgFileId}" alt="头像">
                </div>
                <div class="u_r">
                    <div class="u_r_u">
                        <div class="fr">
                            <img src="/icon/eye.png">
                            <span>${item.count}</span>
                        </div>
                        <div class="title">${item.title}</div>
                    </div>
                    <div class="u_r_d">
                        <span>${item.usrName}</span>
                        <span>${item.createTime}</span>
                        <span>${item.sysCategoryName}</span>
                        <span>${item.perCategoryNames}</span>
                    </div>
                </div>
            </div>
            <div class="desc">${item.text}</div>
        </div>
    </c:forEach>
    <div id="paging"></div>
</div>
