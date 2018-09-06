/**
 *
 * @author xh
 * @date 18-02-24 024
 */
var mouseMoveOn=true;
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
    var lastTop,lastLeft;
    $(document).on("mousemove","body",function (e) {
        if(!mouseMoveOn)return;
        var top = e.pageY - $(this).scrollTop();
        var left = e.pageX;

        var topDistance = top-lastTop;
        var leftDistance = left-lastLeft;
        var distance = 15;
        if(topDistance>-distance&&topDistance<distance&&leftDistance>-distance&&leftDistance<distance){
            return;
        }
        lastTop = top;
        lastLeft = left;

        var index = Math.floor(Math.random()*4+1);
        var img = $("<img class='barrage' src='/icon/star"+index+".png'>");

        $("body").append(img);
        img.css({
            "top":+top+"px",
            "left":left+"px",
            "color":"rgb("+random()+","+random()+","+random()+")"
        });
        img.animate({
            "top":top+120+"px",
            "opacity":0.6
        },1000,function(){
            img.remove();
        });
    });

});