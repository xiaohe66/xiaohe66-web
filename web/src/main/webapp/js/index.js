/**
 * @author xh
 * @time 18-03-21 021
 */
$(function () {




    $(".item").click(function () {
        location.href = "/text/article/detail?id="+$(this).attr("articleId");
    });
});