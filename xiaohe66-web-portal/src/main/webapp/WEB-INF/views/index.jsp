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
    <jsp:include page="/WEB-INF/views/common/init.jsp"></jsp:include>
    <link type="text/css" rel="stylesheet" href="/web/common/css/index.css"/>
    <script type="text/javascript" src="/web/common/js/index.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/top.jsp"></jsp:include>
<div class="c">
    <div class="l">
        <div class="module1">
            <div class="title">快速导航</div>
            <div class="body fast_link">
                <table cellspacing="10px">
                    <thead>
                    <tr>
                        <td><a href="https://www.taobao.com">淘宝</a></td>
                        <td><a href="https://www.jd.com">京东</a></td>
                        <td><a href="https://www.vip.com">唯品会</a></td>
                        <td><a href="http://www.kugou.com">酷狗音乐</a></td>
                        <td><a href="https://y.qq.com">QQ音乐</a></td>
                        <td><a href="http://music.163.com">网易云音乐</a></td>
                    </tr>
                    <tr>
                        <td><a href="http://www.youku.com">优酷</a></td>
                        <td><a href="http://www.iqiyi.com">爱奇艺</a></td>
                        <td><a href="https://v.qq.com/">腾讯视频</a></td>
                        <td><a href="https://www.bilibili.com/">B站</a></td>
                        <td><a href="http://www.huya.com">虎牙</a></td>
                        <td><a href="http://www.douyu.com">斗鱼</a></td>
                    </tr>
                    <tr>
                        <td><a href="https://www.weibo.com">新浪微博</a></td>
                        <td><a href="http://tieba.baidu.com">百度贴吧</a></td>
                        <td><a href="http://www.zhihu.com">知乎</a></td>
                        <td><a href="http://www.jjwxc.net">晋江</a></td>
                        <td><a href="https://www.qidian.com">起点</a></td>
                        <td><a href="http://www.cjzww.com">长江中文网</a></td>
                    </tr>
                    <tr>
                        <td><a href="http://www.people.com.cn">人民网</a></td>
                        <td><a href="http://www.huanqiu.com">环球日报</a></td>
                        <td><a href="http://www.sohu.com">搜狐新闻</a></td>
                        <td><a href="http://www.58pic.com">千图网</a></td>
                        <td><a href="http://www.ctrip.com">携程</a></td>
                        <td><a href="https://kyfw.12306.cn/otn/leftTicket/init">12306</a></td>
                    </tr>
                    <tr>
                        <td><a href="https://pan.baidu.com">百度云</a></td>
                        <td><a href="https://mail.qq.com">QQ邮箱</a></td>
                        <td><a href="http://www.pinterest.com">Pinterest</a></td>
                        <td><a href="http://www.youtube.com">Youtube</a></td>
                        <td><a href="https://twitter.com">推特</a></td>
                        <td><a href="https://www.facebook.com">脸书</a></td>
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
                                <img class="head_img" src="/org/user/file/img/${item.imgFileId}" alt="头像">
                            </div>
                            <div class="u_r">
                                <div class="u_r_u">
                                    <div class="fr">
                                        <img src="/icon/eye.png">
                                        <span>${item.count}</span>
                                            <%--<img src="/icon/praise.png">
                                            <span>1023</span>
                                            <img src="/icon/comment.png">
                                            <span>304</span>
                                            <img src="">--%>
                                    </div>
                                    <div class="title">${item.title}</div>
                                </div>
                                <div class="u_r_d">
                                    <span>${item.userName}</span>
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
    </div>
    <div class="r">
        <div class="module1">
            <div class="title">
                <c:if test="${empty usrDivTitle}">作者</c:if>
                <c:if test="${not empty usrDivTitle}">${usrDivTitle}</c:if>
            </div>
            <div class="body master">
                <img src="/org/user/file/img/${usrDto.imgFileId}" class="head_img" alt="${usrDto.userName}"
                     onclick="location.href = '/text/article/list/${usrDto.id}'">
                <p>${usrDto.userName}</p>
                <p>${usrDto.signature}</p>
            </div>
        </div>
        <div class="module1">
            <div class="title">友情链接</div>
            <div class="body link">
                <a href="http://www.github.com/tiy-he" target="_blank">小何的GitHub</a>
                <a href="https://blog.csdn.net/xiaohe73" target="_blank">小何的Csdn</a>
                <a href="https://love.xiaohe66.com" target="_blank">He&Yan的爱恋</a>
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
