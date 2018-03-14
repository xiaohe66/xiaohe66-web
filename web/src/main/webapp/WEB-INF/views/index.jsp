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
    <link type="text/css" rel="stylesheet" href="/css/index.css"/>

    <script type="text/javascript" src="/js/jquery/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="/js/xh/xh-common.js"></script>
    <script type="text/javascript" src="/js/xh/mouse-anim.js"></script>
    <script type="text/javascript" src="/js/xh/mask.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/top.jsp"></jsp:include>
<div class="c">
    <div class="c_u">
        <div class="left">
            <div class="module1">
                <div class="title">快速导航</div>
                <div class="body fast_link">
                    <table cellspacing="10px">
                        <thead>
                        <tr>
                            <td><a href="javascript:void(0);">链接</a></td>
                            <td><a href="javascript:void(0);">链接</a></td>
                            <td><a href="javascript:void(0);">链接</a></td>
                            <td><a href="javascript:void(0);">链接</a></td>
                            <td><a href="javascript:void(0);">链接</a></td>
                            <td><a href="javascript:void(0);">链接</a></td>
                        </tr>
                        <tr>
                            <td><a href="javascript:void(0);">链接</a></td>
                            <td><a href="javascript:void(0);">链接</a></td>
                            <td><a href="javascript:void(0);">链接</a></td>
                            <td><a href="javascript:void(0);">链接</a></td>
                            <td><a href="javascript:void(0);">链接</a></td>
                            <td><a href="javascript:void(0);">链接</a></td>
                        </tr>
                        <tr>
                            <td><a href="javascript:void(0);">链接</a></td>
                            <td><a href="javascript:void(0);">链接</a></td>
                            <td><a href="javascript:void(0);">链接</a></td>
                            <td><a href="javascript:void(0);">链接</a></td>
                            <td><a href="javascript:void(0);">链接</a></td>
                            <td><a href="javascript:void(0);">链接</a></td>
                        </tr>
                        <tr>
                            <td><a href="javascript:void(0);">链接</a></td>
                            <td><a href="javascript:void(0);">链接</a></td>
                            <td><a href="javascript:void(0);">链接</a></td>
                            <td><a href="javascript:void(0);">链接</a></td>
                            <td><a href="javascript:void(0);">链接</a></td>
                            <td><a href="javascript:void(0);">链接</a></td>
                        </tr>
                        <tr>
                            <td><a href="javascript:void(0);">链接</a></td>
                            <td><a href="javascript:void(0);">链接</a></td>
                            <td><a href="javascript:void(0);">链接</a></td>
                            <td><a href="javascript:void(0);">链接</a></td>
                            <td><a href="javascript:void(0);">链接</a></td>
                            <td><a href="javascript:void(0);">链接</a></td>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
            <div class="module2">
                <div class="title">最新文章</div>
                <div class="body">
                    <div class="item">
                        <div>
                            <div class="fl">
                                <img class="head_img" src="" alt="头像">
                            </div>
                            <div class="u_r">
                                <div class="u_r_u">
                                    <div class="fr">
                                        <img src="/icon/eye.png">
                                        <span>50234</span>
                                        <%--<img src="/icon/praise.png">
                                        <span>1023</span>
                                        <img src="/icon/comment.png">
                                        <span>304</span>
                                        <img src="">--%>
                                    </div>
                                    <div class="title">发现生活的美</div>
                                </div>
                                <div class="u_r_d">
                                    <span>斯文仔</span>
                                    <span>2018-03-06</span>发布时间：
                                    <span>2018-03-13</span>
                                    系统分类：
                                    <span>技术文章</span>
                                    作者分类：
                                    <span>好文</span>
                                </div>
                            </div>
                        </div>
                        <div class="desc">简单的 HTML 表格由 table 元素以及一个或多个 tr、th 或 td 元素组成。tr 元素定义表格行，th 元素定义表头，td 元素定义表格单元。</div>
                    </div>
                    <div class="item">
                        <div>
                            <div class="fl">
                                <img class="head_img" src="" alt="头像">
                            </div>
                            <div class="u_r">
                                <div class="u_r_u">
                                    <div class="fr">
                                        <img src="/icon/eye.png">
                                        <span>50234</span>
                                        <%--<img src="/icon/praise.png">
                                        <span>1023</span>
                                        <img src="/icon/comment.png">
                                        <span>304</span>
                                        <img src="">--%>
                                    </div>
                                    <div class="title">发现生活的美</div>
                                </div>
                                <div class="u_r_d">
                                    <span>斯文仔</span>
                                    <span>2018-03-06</span>发布时间：
                                    <span>2018-03-13</span>
                                    系统分类：
                                    <span>技术文章</span>
                                    作者分类：
                                    <span>好文</span>
                                </div>
                            </div>
                        </div>
                        <div class="desc">简单的 HTML 表格由 table 元素以及一个或多个 tr、th 或 td 元素组成。tr 元素定义表格行，th 元素定义表头，td 元素定义表格单元。</div>
                    </div>
                    <div class="item">
                        <p class="without">
                            <img src="/icon/grieved.png">
                            <span>没有了</span>
                        </p>
                    </div>
                    <div class="item">
                        <p class="without">
                            <img src="/icon/grieved.png">
                            <span>没有了</span>
                        </p>
                    </div>
                    <div class="item">
                        <p class="without">
                            <img src="/icon/grieved.png">
                            <span>没有了</span>
                        </p>
                    </div>
                </div>
            </div>
        </div>
        <div class="right">
            <div class="module1">
                <div class="title">站长</div>
                <div class="body master">
                    <img src="" class="head_img" alt="站长" onclick="location.href = '/list'">
                    <p>小何</p>
                    <p>一个梦想成为大佬的屌丝程序员</p>
                </div>
            </div>
            <div class="module1">
                <div class="title">资源下载</div>
                <div class="body source">

                </div>
            </div>
            <div class="module1">
                <div class="title">文章分类</div>
                <div class="body category">

                </div>
            </div>
        </div>
    </div>
</div>
<div class="f">

</div>
</body>
</html>
