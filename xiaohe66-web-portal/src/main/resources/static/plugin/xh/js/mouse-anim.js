/**
 *
 * @author xh
 * @date 18-02-24 024
 */
let mouseMoveOn = true;
let mouseClickOn = true;
let loveShowOn = false;
$(function () {

    if (loveShowOn) {
        let love = $("<div id=\"love\"><a href=\"javascript:\" class=\"close\">关闭</a><div><img src=\"/icon/mylove.png\"></div></div>");
        $("body").append(love);
        let close = love.find(".close");

        love.find("img").click(function () {
            window.open("https://love.xiaohe66.com");
        });

        let maxHeight = document.body.clientHeight - 150;
        let maxWidth = document.body.clientWidth - 300;

        let w = Math.floor(Math.random() * maxWidth);
        let h = Math.floor(Math.random() * maxHeight);

        console.log("maxHeight", maxHeight);
        console.log("maxWidth", maxWidth);


        let wDivisor = 1;
        let hDivisor = 1;
        let speed = 1;
        let isMove = true;

        let timer = setInterval(function () {
            if(!isMove)return;
            love.css({top: h + "px", left: w + "px"});
            w += (speed * wDivisor);
            h += (speed * hDivisor);
            if (h > maxHeight) {
                console.log("切换h", h, hDivisor);
                hDivisor = -1;
            } else if (h <= 0) {
                console.log("切换h", h, hDivisor);
                hDivisor = 1;
            }
            if (w >= maxWidth) {
                wDivisor = -1;
                console.log("切换w", w, wDivisor);
            } else if (w <= 0) {
                wDivisor = 1;
                console.log("切换w", w, wDivisor);
            }
        }, 8);

        close.click(function () {
            loveShowOn = false;
            love.hide();
            clearInterval(timer);
        });
        love.on({
            mouseover: function () {
                close.show();
                love.css({opacity: "1"});
                isMove = false;
            },
            mouseout: function () {
                close.hide();
                love.css({opacity: "0.7"});
                isMove = true;
            }
        });
    }
    $(document).on("click", "body", function (e) {
        if (!mouseClickOn) return;

        let index = Math.floor(Math.random() * 7);

        let div = $("<div class='barrage'></div>");
        div.css({"background-position": (-40 * index - 20) + "px -20px"});

        $("body").append(div);
        let top = e.pageY - $(this).scrollTop() - 20;
        div.css({
            "top": +top + "px",
            "left": e.pageX - 10 + "px"
        });
        div.animate({
            "top": top - 250 + "px",
            "opacity": 0.2
        }, 1500, function () {
            div.remove();
        });
    });
    let lastTop, lastLeft;
    $(document).on("mousemove", "body", function (e) {
        if (!mouseMoveOn) return;
        let top = e.pageY - $(this).scrollTop() + 10;
        let left = e.pageX + 6;

        let topDistance = top - lastTop;
        let leftDistance = left - lastLeft;
        let distance = 15;
        if (topDistance > -distance && topDistance < distance && leftDistance > -distance && leftDistance < distance) {
            return;
        }
        lastTop = top;
        lastLeft = left;

        let index = Math.floor(Math.random() * 4 + 1);
        let img = $("<img class='barrage' alt='' src='/icon/star" + index + ".png'>");

        $("body").append(img);
        img.css({
            "top": +top + "px",
            "left": left + "px"
        });
        img.animate({
            "top": top + 150 + "px",
            "opacity": 0.6
        }, 1200, function () {
            img.remove();
        });
    });

});