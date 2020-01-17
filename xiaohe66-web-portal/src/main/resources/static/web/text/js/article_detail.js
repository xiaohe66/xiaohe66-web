/**
 * @author  xiaohe
 * @time    17-11-12 012
 */
$(function(){
    post("/text/article/log");

    $(".editor img").click(function () {
        var src = $(this).attr('src');

        var html = "<img class='img_viewer' src='"+src+"'><img>"

        $.maskHtml(html,function () {

        });
    });
});