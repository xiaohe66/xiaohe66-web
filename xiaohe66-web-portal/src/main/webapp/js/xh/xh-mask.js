/**
 * Created by xh on 18-03-08 008.
 */
(function($){

    var close;
    var closeOn = true;
    var mask = $("<div id=\"mask\"></div>");
    var hint = $("<div id=\"hint\"></div>");

    var maskClose = function () {
        mask.hide();
        if(close){
            close();
        }
    };

    $(function () {
        $("body").append(mask);
        mask.click(maskClose);
        mask.append(hint);
    });
    $.extend({
        mask : function (closeFunc) {
            close = closeFunc;
            hint.hide();
            mask.show();
        },
        maskHint : function (msg,closeFunc) {
            close = closeFunc;
            hint.text(msg);
            mask.show();
        },
        maskClose : maskClose,
        hint : function (msg) {
            closeOn = false;
            hint.text(msg);
            mask.show();
        },
        hintClose : function () {
            closeOn = true;
            mask.hide();
        }
    });

})(jQuery);