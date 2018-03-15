<%--
  Created by IntelliJ IDEA.
  User: xh
  Date: 18-03-15 015
  Time: 10:02 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>留言板</title>
    <link type="text/css" rel="stylesheet" href="/css/xh/xh-common.css"/>
    <link type="text/css" rel="stylesheet" href="/web/text/css/message_board.css"/>

    <script type="text/javascript" src="/js/jquery/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="/js/editor/wangEditor.min.js"></script>
    <script type="text/javascript" src="/js/xh/xh-mask.js"></script>
    <script type="text/javascript" src="/js/xh/xh-common.js"></script>
    <script type="text/javascript" src="/js/xh/mouse-anim.js"></script>
    <script type="text/javascript" src="/web/text/js/message_board.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/top.jsp"></jsp:include>
<div class="c">
    <div class="left">
        <div class="hint border1">
            <p class="title">发表留言</p>
            <p class="msg"><a href="javascript:showLogin();">登录</a>后才能留言</p>
        </div>
        <div id="edit" class="border1">
            <div class="tool"></div>
            <div class="editor"></div>
        </div>
        <div class="item">
            <div>
                <div class="fl">
                    <img class="head_img" src="" alt="头像">
                </div>
                <div class="u_r">
                    <div class="u_r_d">
                        <span>斯文仔</span>
                        <span>2018-03-06</span>
                        <span>1楼</span>
                    </div>
                    <div class="desc">简单的 HTML 表格由 table 元素以及一个或多个 tr、th 或 td 元素组成。tr 元素定义表格行，th 元素定义表头，td 元素定义表格单元。</div>
                </div>
            </div>
        </div>
        <div class="item">
            <div>
                <div class="fl">
                    <img class="head_img" src="" alt="头像">
                </div>
                <div class="u_r">
                    <div class="u_r_d">
                        <span>斯文仔</span>
                        <span>2018-03-06</span>
                        <span>1楼</span>
                    </div>
                    <div class="desc">简单的 HTML 表格由 table 元素以及一个或多个 tr、th 或 td 元素组成。tr 元素定义表格行，th 元素定义表头，td 元素定义表格单元。</div>
                </div>
            </div>
        </div>
    </div>
    <div class="right">
        <div class="module1">
            <div class="title">作者</div>
            <div class="body master">
                <img src="" class="head_img" alt="站长">
                <p>小何</p>
                <p>一个梦想成为大佬的屌丝程序员</p>
            </div>
        </div>
        <div class="module1">
            <div class="title">作者分类</div>
            <div class="body category">
                <p><a href="javascript:void(0);">分类(20)</a></p>
                <p><a href="javascript:void(0);">分类(20)</a></p>
                <p><a href="javascript:void(0);">分类(20)</a></p>
                <p><a href="javascript:void(0);">分类(20)</a></p>
                <p><a href="javascript:void(0);">分类(20)</a></p>
                <p><a href="javascript:void(0);">分类(20)</a></p>
            </div>
        </div>
    </div>
</div>
<div class="f"></div>
</body>
</html>
