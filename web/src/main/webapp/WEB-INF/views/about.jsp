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
    <title>关于</title>
    <link type="text/css" rel="stylesheet" href="/css/xh/xh-common.css"/>
    <link type="text/css" rel="stylesheet" href="/css/about.css"/>

    <script type="text/javascript" src="/js/jquery/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="/js/xh/xh-common.js"></script>
    <script type="text/javascript" src="/js/xh/mouse-anim.js"></script>
    <script type="text/javascript" src="/js/xh/xh-mask.js"></script>
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/top.jsp"></jsp:include>
<div class="c">
    <div class="c_c border1">
        <p>关于</p>
        <div class="web">
            <p>关于本站</p>
            <ul>
                <li>本站是由小何一人独立开发，并自主维护的一个小站。</li>
                <li>后端采用springMVC+myBatis+shrio框架，并使用maven管理依赖。使用mysql数据库，tomcat服务器。</li>
                <li>前端使用了开源框架jQuery.js、wangEditor.js、spark-md5.js。</li>
                <li class="func">
                    目前已实现功能：
                    <ul>
                        <li>文章发表</li>
                        <li>留言板</li>
                        <li>文件上传（采用文件的md5判断，实现不重复上传相同的文件）</li>
                    </ul>
                </li>
            </ul>
        </div>
        <div class="author">
            <p>关于站长</p>
            <ul>
                <li>小何，英文名xiaohe，广东河源龙川人。从小喜欢下棋、玩游戏，并励志成为软件攻城狮。
                    高考如愿以尝，被录取到广东职业技术学院的游戏制作专业，并于2016年6月顺利毕业。</li>
                <li>然而，由于过于迷恋游戏，在玩完大专3年后，苦于无法找到工作。但是在找工作的过程中，遇到许多培训机构，并在宣传他们的java课程。
                在经历了一段时间的迷茫之后，决定自学当时最热门的java。由于有c++的基础，很快便学完基础，接着就出来找工作了。
                经过多次碰壁之后（基本上都是培训机构的邀请），发现java有android和web两大方向，认识到自己的不足之后，又回到学校宿舍自学android。</li>
                <li>在学完android基础之后，又出来找工作。这次终于被一家创业公司以“java开发学徒”的名义收留，但由于能力问题很快被辞退。
                    后来买了一本书《java web整合项目实战》，在学了一个月之后，找到了真正意义的第一份正式java工作，从此走上了程序员这条不归路。</li>
            </ul>
        </div>
    </div>
</div>
<div class="f"></div>
</body>
</html>
