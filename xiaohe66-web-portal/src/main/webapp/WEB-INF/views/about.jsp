<%--
  Created by IntelliJ IDEA.
  User: xh
  Date: 18-03-25 025
  Time: 18:38 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/views/common/init.jsp"></jsp:include>
    <link type="text/css" rel="stylesheet" href="/css/about.css"/>

</head>
<body>
    <jsp:include page="/WEB-INF/views/common/top.jsp"></jsp:include>
<div class="c">
    <div class="c_c border1">
        <p>关于</p>
        <div class="web">
            <p>关于本站</p>
            <ul>
                <li>本站由小何一人独立开发，包括原型设计、UI设计、前端开发、后端开发、数据库设计、架构设计等。于2018年5月1号上线，并由小何本人自主维护运行。</li>
                <li>后端采用springMVC+myBatis+shrio框架，并使用maven管理依赖。使用mysql数据库，tomcat应用服务器，并在linux环境上部署。</li>
                <li>前端使用了开源框架jQuery.js、wangEditor.js、spark-md5.js。</li>
                <li>本站旨在锻炼小何的动手能力和成为一个展现自我的平台。</li>
                <li class="func">
                    目前已实现功能：
                    <ul>
                        <li>快速导航</li>
                        <li>文章发表</li>
                        <li>留言板</li>
                        <li>文件共享（断点续传）</li>
                    </ul>
                </li>
            </ul>
        </div>
        <div class="author">
            <p>关于站长</p>
            <ul>
                <li>小何，英文名xiaohe，广东河源龙川人，2016年毕业于广东职业技术学院。</li>
                <li>喜欢下棋、玩电脑。热爱编程，并自学成才。</li>
                <li class="information">
                    联系站长：
                    <ul>
                        <li>住址：广州天河</li>
                        <li>手机：18565475317</li>
                        <li>微信：18565475317</li>
                        <li>邮箱：tiy_he@foxmail.com</li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/views/common/foot.jsp"></jsp:include>
</body>
</html>
