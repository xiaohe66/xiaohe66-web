<%--
  Created by IntelliJ IDEA.
  User: xiaohe
  Date: 17-11-12 012
  Time: 21:40 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/xh/xh-paging.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/web/text/css/article_list.css"/>">
<script type="text/javascript" src="<c:url value="/js/xh/xh-paging.js"/>"></script>
<script>
    $(function () {
        $(document).on("click",".item",function () {
           location.href = "/text/article/detail/"+$(this).attr("id");
        });
        var html = $(".item")[0].outerHTML;
        $("#paging").paging(${pageInfo.pages},${pageInfo.pageNum},function (page) {
            $.getPaging("/text/article",page,10,{lookUsrId:"${lookUsrId}"},function (arr) {
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
                    item.find(".fr").find("span").text(data.count);
                    item.find(".digest").html(data.text);
                });
            });
        });
    });
</script>
<c:if test="${pageInfo.pages==0}">
    <div class="item">
        <p style="text-align: center">
            <img style="width: 64px;height: 64px" src="/icon/grieved.png">
            <span style="vertical-align: middle;display: inline-block;margin-left:
            30px;font-size: 30px;line-height: 64px;">作者还没有文章</span>
        </p>
    </div>
</c:if>
<c:forEach items="${pageInfo.list}" var="item" end="9">
    <div class="item" id="${item.id}">
        <p class="title">${item.title}</p>
        <div class="fr">
            <img src="/icon/eye.png">
            <span>${item.count}</span>
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
