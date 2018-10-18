<%--
  Created by IntelliJ IDEA.
  User: xh
  Date: 18-10-18 018
  Time: 17:40 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/views/common/init.jsp"></jsp:include>
    <style>
        .c_c{
            margin: 0 auto;
            width: 60%;
            text-align: center;
            padding-top: 100px;
        }
        .c_c img{
            width: 260px;
            height: 260px;
        }
        .c_c .msg{
            margin-top: 50px;
            font-size: 24px;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/top.jsp"></jsp:include>
    <div class="c">
        <div class="c_c">
            <img src="/icon/grieved1.png">
            <div class="msg">${msg}</div>
        </div>
    </div>
    <jsp:include page="/WEB-INF/views/common/foot.jsp"></jsp:include>
</body>
</html>
