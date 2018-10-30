<%--
  Created by IntelliJ IDEA.
  User: xh
  Date: 18-09-06 006
  Time: 16:24 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="/WEB-INF/views/common/init.jsp"></jsp:include>
    <link type="text/css" rel="stylesheet" href="/web/common/css/right.css"/>
    <link rel="stylesheet" type="text/css" href="/web/resume/css/resume_index.css">

    <script type="text/javascript" src="/js/echarts/echarts.min.js"></script>

    <script>
        var abilityJson = ${resumeMain.abilityJson};
    </script>
    <script type="text/javascript" src="/web/resume/js/resume_index.js"></script>

</head>
<body>
<jsp:include page="/WEB-INF/views/common/top.jsp"></jsp:include>
<div class="c">
    <div class="l">
        <div class="module1">
            <div class="title">个人资料</div>
            <div class="body base">
                <div><div>姓名：</div>${resumeMain.name}</div>
                <div><div>电话：</div>${resumeMain.phone}</div>
                <div><div>邮箱：</div>${resumeMain.email}</div>
                <div><div>学历：</div>${resumeMain.education}</div>
                <div><div>毕业院校：</div>${resumeMain.graduationSchool}</div>
                <div><div>毕业时间：</div>${resumeMain.graduationDate}</div>
            </div>
        </div>
        <div class="module1 jobs">
            <div class="title">工作经历</div>
            <div class="body">
                <c:forEach items="${resumeMain.resumeJobDtoList}" var="item">
                    <div class="item">
                        <div class="fl">
                            <img class="logo" src="/org/usr/file/img/${item.imgFileId}" alt="logo">
                        </div>
                        <div class="u_r">
                            <div class="name">${item.orgName}</div>
                            <div class="time">${item.startDate}-${item.endDate}</div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="module1 projects">
            <div class="title">项目经历</div>
            <div class="body">
                <c:forEach var="item" items="${resumeMain.resumeProjectDtoList}">
                    <div class="item">
                        <div class="fl">
                            <img class="logo" src="/org/usr/file/img/${item.imgFileId}" alt="logo">
                        </div>
                        <div class="u_r">
                            <div class="name">${item.projectName}</div>
                            <div class="desc">${item.projectDesc}</div>
                        </div>
                        <div class="light">
                            <div>个人成果/亮点：</div>
                            <ul>
                                <c:forEach items="${item.resumeFuncDtoList}" var="func">
                                    <li><a href="/text/article/detail/${func.articleId}" target="_blank">${func.funcName}</a></li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="module1 ability">
            <div class="title">个人能力</div>
            <div class="body">
                <p><span>较为突出：</span>${resumeMain.abilityDesc}</p>
            </div>
        </div>
    </div>
    <div class="r">
        <div class="module1">
            <div class="title">小何</div>
            <div class="body master">
                <img src="/org/usr/file/img/${usrDto.imgFileId}" class="head_img" alt="${usrDto.usrName}"
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
            </div>
        </div>
        <div class="module1">
            <div class="title">
                他的热门文章(近30天)
                <a href="/text/article/all">more>></a>
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

