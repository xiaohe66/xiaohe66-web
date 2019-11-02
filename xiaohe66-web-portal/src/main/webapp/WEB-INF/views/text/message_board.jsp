<%--
  Created by IntelliJ IDEA.
  User: xh
  Date: 18-03-15 015
  Time: 10:02 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <jsp:include page="/WEB-INF/views/common/init.jsp"></jsp:include>
    <link type="text/css" rel="stylesheet" href="/web/common/css/right.css"/>
    <link type="text/css" rel="stylesheet" href="/plugin/xh/css/xh-paging.css"/>
    <link type="text/css" rel="stylesheet" href="/web/text/css/message_board.css"/>
    <script type="text/javascript" src="/plugin/xh/js/xh-paging.js"></script>
    <script type="text/javascript" src="/web/text/js/message_board.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/top.jsp"></jsp:include>
<div class="c">
    <div class="l">
        <shiro:guest>
            <input type="hidden" id="isLogin" value="0">
        </shiro:guest>
        <shiro:authenticated>
            <input type="hidden" id="isLogin" value="1">
        </shiro:authenticated>
        <div class="msg border1">
            <p class="title">发表留言</p>
            <div class="edit">
                <textarea placeholder="你在本站遇到的bug，或你想对站长说的话"></textarea>
            </div>
            <a class="btn">发表留言</a>
            <shiro:guest>
                <input class="anonymityOn" type="checkbox"
                       checked="checked">匿名<input class="anonymity" placeholder="匿名名称">
            </shiro:guest>
            <shiro:authenticated>
                <input class="anonymityOn"
                       type="checkbox">匿名<input style="display: none;" class="anonymity" placeholder="匿名名称">
            </shiro:authenticated>
        </div>
        <div class="content">
            <div class="item">
                <div>
                    <div class="fl">
                        <img class="head_img" src="/org/user/file/img/0" alt="头像">
                    </div>
                    <div class="u_r">
                        <div class="u_r_d">
                            <span></span>
                            <span></span>
                            <span></span>
                        </div>
                        <div class="desc"></div>
                    </div>
                </div>
            </div>
        </div>
        <div id="paging"></div>
    </div>
    <div class="r">
        <div class="module1">
            <div class="title">
                <c:if test="${empty userDivTitle}">作者</c:if>
                <c:if test="${not empty userDivTitle}">${userDivTitle}</c:if>
            </div>
            <div class="body master">
                <img src="/org/user/file/img/${lookAtUser.imgFileId}" class="head_img" alt="${lookAtUser.usrName}"
                     onclick="location.href = '/text/article/list/${lookAtUser.id}'">
                <p>${lookAtUser.usrName}</p>
                <p>${lookAtUser.signature}</p>
            </div>
        </div>
        <div class="module1">
            <div class="title">友情链接</div>
            <div class="body link">
                <a href="http://www.github.com/tiy-he" target="_blank">小何的GitHub</a>
                <a href="https://blog.csdn.net/xiaohe73" target="_blank">小何的Csdn</a>
                <a href="https://love.xiaohe66.com" target="_blank">love.xiaohe66.com</a>
            </div>
        </div>
        <div class="module1">
            <div class="title">
                <c:if test="${empty userDivTitle}">他的</c:if>
                热门资源(近30天)
                <c:if test="${not empty userDivTitle}">
                    <a href="/org/usr/file/all">more>></a>
                </c:if>
            </div>
            <div class="body source">
                <c:forEach items="${fileList}" var="item">
                    <a href="/org/usr/file/download/${item.id}">${item.fileName}${item.extension}<span>${item.downloadCount}</span></a>
                </c:forEach>
            </div>
        </div>
        <div class="module1">
            <div class="title">
                <c:if test="${empty userDivTitle}">他的</c:if>
                热门文章(近30天)
                <c:if test="${not empty userDivTitle}">
                    <a href="/text/article/all">more>></a>
                </c:if>
            </div>
            <div class="body article">
                <c:forEach items="${hotArticle}" var="item">
                    <a href="/text/article/detail/${item.id}">${item.title}<span>${item.count}</span></a>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/views/common/foot.jsp"></jsp:include>
</body>
</html>