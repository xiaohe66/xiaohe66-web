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

    <link rel="stylesheet" type="text/css" href="/css/xh/xh-common.css">
    <link rel="stylesheet" type="text/css" href="/web/text/css/article_editor.css">

    <script type="text/javascript" src="/js/jquery/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="/js/editor/wangEditor.min.js"></script>
    <script type="text/javascript" src="/js/xh/xh-common.js"></script>
    <script type="text/javascript" src="/js/xh/mouse-anim.js"></script>
    <script type="text/javascript" src="/web/text/js/article_editor.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/top.jsp"></jsp:include>
<div class="c">
    <c:if test="${not empty article}">
        <input id="id" type="hidden" value="${article.id}">
    </c:if>
    <div>
        <label>
            <input class="title" placeholder="请输入文章标题" <c:if test="${not empty article}">value="${article.title}"</c:if>/>
        </label>
    </div>
    <div class="tool"></div>
    <div class="editor border1">
        <c:if test="${not empty article}"><p>${article.text}</p></c:if>
    </div>
    <div class="option">
        <ul>
            <li>
                <span>系统分类</span>
                <div>
                    <%--<p><a href="javascript:void(0);">添加新分类</a></p>--%>
                    <div class="category border1">
                        <label>
                        <input type="radio" name="category">
                        技术文档
                        </label>
                        <label>
                            <input type="radio" name="category">
                            心情琐事
                        </label>
                        <label>
                            <input type="radio" name="category">
                            生活科普
                        </label>
                    </div>
                </div>
            </li>
            <li>
                <span>个人分类</span>
                <div>
                    <p><a href="javascript:addCategory();">添加新分类</a></p>
                    <div class="category border1" id="categoryPerson">
                        <%--<label>
                            <input type="checkbox">
                            mysql
                        </label>--%>
                    </div>
                </div>
            </li>
            <li>
                <span>是否公开</span>
                <div>
                    <div class="category circle">
                        <label>
                            <input type="radio" name="1">
                            公开
                        </label>
                        <label>
                            <input type="radio" name="1">
                            好友可见
                        </label>
                        <label>
                            <input type="radio" name="1">
                            私密
                        </label>
                    </div>
                </div>
            </li>
        </ul>
        <div class="btn_d">
            <a class="btn">保存草稿</a>
            <a class="btn" href="javascript:publish();">发表文章</a>
        </div>
    </div>
</div>
<div class="f"></div>
</body>
</html>
