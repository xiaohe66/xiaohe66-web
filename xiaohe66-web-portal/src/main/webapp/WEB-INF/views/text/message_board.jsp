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
        <input type="hidden" id="usrId" value="${usr.id}">
        <input type="hidden" id="size" value="${page.pages}">
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
            <c:if test="${page.pages==0}">
                <div class="item">
                    <p style="text-align: center">
                        <img style="vertical-align: middle" src="/icon/grieved.png">
                        <span style="vertical-align: middle;display: inline-block;margin-left:
                30px;font-size: 30px;line-height: 64px;">暂无留言</span>
                    </p>
                </div>
            </c:if>
            <c:forEach items="${page.records}" var="item" varStatus="statu">
                <div class="item">
                    <div>
                        <div class="fl">
                            <img class="head_img" src="/org/user/file/img/${item.imgFileId}" alt="头像">
                        </div>
                        <div class="u_r">
                            <div class="u_r_d">
                                <span>${item.usrName}</span>
                                <span>${item.createTime}</span>
                                <span>${item.id}楼</span>
                            </div>
                            <div class="desc">${item.msg}</div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div id="paging"></div>
    </div>
    <div class="r">
        <div class="module1">
            <div class="title">
                <c:if test="${empty usrDivTitle}">作者</c:if>
                <c:if test="${not empty usrDivTitle}">${usrDivTitle}</c:if>
            </div>
            <div class="body master">
                <img src="/org/user/file/img/${usrDto.imgFileId}" class="head_img" alt="${usrDto.usrName}"
                     onclick="location.href = '/text/article/list/${usrDto.id}'">
                <p>${usrDto.usrName}</p>
                <p>${usrDto.signature}</p>
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
                <c:if test="${empty usrDivTitle}">他的</c:if>
                热门资源(近30天)
                <c:if test="${not empty usrDivTitle}">
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
                <c:if test="${empty usrDivTitle}">他的</c:if>
                热门文章(近30天)
                <c:if test="${not empty usrDivTitle}">
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