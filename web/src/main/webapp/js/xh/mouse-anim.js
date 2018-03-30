/**
 *
 * @author xh
 * @date 18-02-24 024
 */
$(function () {
    var str = ["富强","民主","文明","和谐","自由","平等","公正","法治","爱国","敬业","诚信","友善"];

    var random = function () {
        return Math.floor(Math.random()*256);
    };

    $(document).on("click","body",function (e) {
        var index = Math.floor(Math.random()*str.length);
        var div = $("<div class='barrage'>"+str[index]+"</div>");
        $("body").append(div);
        var top = e.pageY - $(this).scrollTop();
        div.css({
            "top":+top+"px",
            "left":e.pageX+"px",
            "color":"rgb("+random()+","+random()+","+random()+")"
        });
        div.animate({
            "top":top-200+"px",
            "opacity":0.5
        },1300,function(){
            div.remove();
        });
    });

});