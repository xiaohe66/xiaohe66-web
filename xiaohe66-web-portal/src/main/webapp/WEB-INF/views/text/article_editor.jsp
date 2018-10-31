<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: xiaohe
  Date: 17-11-12 012
  Time: 0:10 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="/plugin/editor/css/wangEditor.min.css">
<link rel="stylesheet" type="text/css" href="/plugin/xh/css/xh-validator.css">
<link rel="stylesheet" type="text/css" href="/web/text/css/article_editor.css">

<script type="text/javascript" src="/web/common/js/spark-md5.min.js"></script>
<script type="text/javascript" src="/plugin/editor/js/wangEditor.min.js"></script>
<script type="text/javascript" src="/plugin/xh/js/xh-validator.js"></script>
<script>
    var articleId = "${article.id}";
    var secretLevel = "${article.secretLevel}";
    var sysCategoryId = "${article.sysCategoryId}";
    var perCategoryIds = "${article.perCategoryIds}";
</script>
<script type="text/javascript" src="/web/text/js/article_editor.js"></script>
<div class="editorMain">
    <div>
        <input id="articleTitle" class="title" placeholder="请输入文章标题" <c:if test="${not empty article}">value="${article.title}"</c:if>/>
        <div class="warn"></div>
    </div>
    <div class="editor1">
        <c:if test="${not empty article}"><p>${article.text}</p></c:if>
    </div>
    <div class="option">
        <ul>
            <li>
                <span>系统分类</span>
                <div>
                    <div class="category border1" id="sysCategory">
                        <c:forEach items="${sysCategoryList}" var="item">
                            <label><input type="radio" name="category"
                                <c:if test="${not empty article && article.sysCategoryName.equals(item.categoryName)}">checked="checked"</c:if>
                                          value="${item.id}">${item.categoryName}</label>
                        </c:forEach>
                    </div>
                </div>
            </li>
            <li>
                <span>个人分类</span>
                <div>
                    <p class="addCategory">
                        <c:if test="${perCategorySize < 10}"><a>添加新分类</a></c:if>
                        <c:if test="${perCategorySize >= 10}"><span>最多添加10个分类，请前往个人中心编辑!</span></c:if>
                    </p>
                    <div class="category border1" id="perCategory">
                        <c:forEach items="${perCategoryList}" var="item">
                            <label><input type="checkbox" value="${item.id}">${item.categoryName}</label>
                        </c:forEach>
                    </div>
                </div>
            </li>
            <li>
                <span>是否公开</span>
                <div>
                    <div class="secretLevel border1">
                        <label><input type="radio" name="secretLevel" value="0" />公开</label>
                        <label><input type="radio" name="secretLevel" value="1" />私密</label>
                        <c:if test="${usr.id == 3}">
                            <label><input type="radio" name="secretLevel" value="2" />简历</label>
                        </c:if>
                    </div>
                </div>
            </li>
        </ul>
        <div class="btn_d">
            <%--<a class="btn">保存草稿</a>--%>
            <a id="publish" class="btn">发表文章</a>
        </div>
    </div>
</div>
