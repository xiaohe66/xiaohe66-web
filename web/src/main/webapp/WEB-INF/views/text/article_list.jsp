<%--
  Created by IntelliJ IDEA.
  User: xiaohe
  Date: 17-11-12 012
  Time: 21:40 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>文章列表</title>

    <link rel="stylesheet" type="text/css" href="<c:url value="/css/xh/xh-common.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/xh/xh-paging.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/web/text/css/article_list.css"/>">

    <script type="text/javascript" src="<c:url value="/js/jquery/jquery-3.1.1.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/xh/xh-common.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/xh/xh-paging.js"/>"></script>
    <script type="text/javascript" src="/js/xh/xh-mask.js"></script>
    <script>
        $(function () {
            $(document).on("click",".item",function () {
               location.href = "/text/article/detail?id=" + $(this).attr("id");
            });
            var html = $(".item")[0].outerHTML;
            var size = ${size};
            $("#paging").paging(Math.ceil(size/5),1,function (page) {
               $.http("get","/text/article",{
                   lookUsrId:"${lookUsrId}"
               },{
                   pageSize:5,
                   pageNum:page
               },function (arr) {
                   console.log("调用了");
                   var div = $(".l");
                   div.find(".item").remove();
                   $.each(arr,function (i, data) {
                       $("#paging").before(html);
                       var item = div.find(".item:last");
                       item.attr("id",data.id);
                       item.find(".title").text(data.title);
                       var spans = item.find(".desc").find("span");
                       spans.eq(0).text(data.createTime);
                       spans.eq(1).text(data.sysCategoryName);
                       spans.eq(2).text(data.perCategoryNames);
                       item.find(".digest").text(data.text);
                   });
               });
            });
        });
    </script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/top.jsp"></jsp:include>
<div class="c">
    <div class="l">
        <c:forEach items="${list}" var="item">
            <div class="item" id="${item.id}">
                <p class="title">${item.title}</p>
                <div class="fr">
                    <img src="/icon/eye.png">
                    <span>0</span>
                    <%--<img src="/icon/praise.png">
                    <span>1023</span>
                    <img src="/icon/comment.png">
                    <span>304</span>--%>
                </div>
                <div class="desc">
                    <span>${item.createTime}</span>
                    <span>${item.sysCategoryName}</span>
                    <span>${item.perCategoryNames}</span>
                </div>
                <p class="digest">${item.text}</p>
            </div>
        </c:forEach>
        <div id="paging"></div>
    </div>
    <div class="r">
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
