/**
 *
 * @author xh
 * @date 18-02-24 024
 */
var mouseMoveOn=true;
var mouseClickOn=true;
$(function () {

    $(document).on("click","body",function (e) {
        if(!mouseClickOn)return;

        var index = Math.floor(Math.random()*7);

        var div = $("<div class='barrage'></div>");
        div.css({"background-position":(-40*index-20)+"px -20px"});

        $("body").append(div);
        var top = e.pageY - $(this).scrollTop();
        div.css({
            "top":+top+"px",
            "left":e.pageX+"px"
        });
        div.animate({
            "top":top-250+"px",
            "opacity":0.2
        },1500,function(){
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
        var img = $("<img class='barrage' alt='' src='/icon/star"+index+".png'>");

        $("body").append(img);
        img.css({
            "top":+top+"px",
            "left":left+"px"
        });
        img.animate({
            "top":top+150+"px",
            "opacity":0.6
        },1200,function(){
            img.remove();
        });
    });

});