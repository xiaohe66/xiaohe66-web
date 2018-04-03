<%--
  Created by IntelliJ IDEA.
  User: xiaohe
  Date: 17-10-29 029
  Time: 9:26 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link type="text/css" rel="stylesheet" href="/css/index.css"/>
<script type="text/javascript" src="/js/index.js"></script>
<div class="module1">
    <div class="title">快速导航</div>
    <div class="body fast_link">
        <table cellspacing="10px">
            <thead>
            <tr>
                <td><a href="https://www.taobao.com"><img src="#" style="width: 16px;height: 16px;vertical-align: middle;border: 1px solid #eee">淘宝</a></td>
                <td><a href="https://www.jd.com">京东</a></td>
                <td><a href="https://www.vip.com">唯品会</a></td>
                <td><a href="http://www.youku.com">优酷</a></td>
                <td><a href="http://www.iqiyi.com">爱奇艺</a></td>
                <td><a href="https://v.qq.com">腾讯视频</a></td>
            </tr>
            <tr>
                <td><a href="http://www.baidu.com">链接</a></td>
                <td><a href="http://www.baidu.com">链接</a></td>
                <td><a href="http://www.baidu.com">链接</a></td>
                <td><a href="http://www.kugou.com/yy/html/rank.html?from=homepage">酷狗音乐</a></td>
                <td><a href="http://www.baidu.com">链接</a></td>
                <td><a href="http://www.baidu.com">链接</a></td>
            </tr>
            <tr>
                <td><a href="http://www.baidu.com">链接</a></td>
                <td><a href="http://www.baidu.com">链接</a></td>
                <td><a href="http://www.baidu.com">链接</a></td>
                <td><a href="http://www.baidu.com">链接</a></td>
                <td><a href="http://www.baidu.com">链接</a></td>
                <td><a href="http://www.baidu.com">链接</a></td>
            </tr>
            <tr>
                <td><a href="http://www.baidu.com">链接</a></td>
                <td><a href="http://www.baidu.com">链接</a></td>
                <td><a href="http://www.baidu.com">链接</a></td>
                <td><a href="http://www.baidu.com">链接</a></td>
                <td><a href="http://www.baidu.com">链接</a></td>
                <td><a href="http://www.baidu.com">链接</a></td>
            </tr>
            <tr>
                <td><a href="http://www.baidu.com">链接</a></td>
                <td><a href="http://www.baidu.com">链接</a></td>
                <td><a href="http://www.baidu.com">链接</a></td>
                <td><a href="http://www.baidu.com">链接</a></td>
                <td><a href="http://www.baidu.com">链接</a></td>
                <td><a href="http://www.baidu.com">链接</a></td>
            </tr>
            </thead>
        </table>
    </div>
</div>
<div class="module2">
    <div class="title">最新文章</div>
    <div class="body">
        <c:forEach items="${list}" var="item">
            <div class="item" articleId="${item.id}">
                <div>
                    <div class="fl">
                        <img class="head_img" src="" alt="头像">
                    </div>
                    <div class="u_r">
                        <div class="u_r_u">
                            <div class="fr">
                                <img src="/icon/eye.png">
                                <span>0</span>
                                <%--<img src="/icon/praise.png">
                                <span>1023</span>
                                <img src="/icon/comment.png">
                                <span>304</span>
                                <img src="">--%>
                            </div>
                            <div class="title">${item.title}</div>
                        </div>
                        <div class="u_r_d">
                            <span>斯文仔</span>
                            <span>${item.createTime}</span>
                            <span>${item.sysCategoryName}</span>
                            <span>${item.perCategoryNames}</span>
                        </div>
                    </div>
                </div>
                <div class="desc">${item.text}</div>
            </div>
        </c:forEach>
        <c:forEach begin="${list.size()}" end="4">
            <div class="item">
                <p class="without">
                    <img src="/icon/grieved.png">
                    <span>没有了</span>
                </p>
            </div>
        </c:forEach>
    </div>
</div>
