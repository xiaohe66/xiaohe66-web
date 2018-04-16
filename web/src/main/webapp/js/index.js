/**
 * @author xh
 * @time 18-03-21 021
 */
$(function () {

    $(".fast_link").find("tr").each(function (i, tr) {
        $(this).find("a").each(function (j, a) {
            var span = $("<span></span>");
            span.css({"background-position":(-32*j-16)+"px "+(-32*i-16)+"px"});
            $(this).prepend(span);
            $(this).attr("target","_blank");
        });
    });

    $(".item").click(function () {
        location.href = "/text/article/detail/"+$(this).attr("articleId");
    });
});