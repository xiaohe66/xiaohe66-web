/**
 * Created by xh on 18-03-08 008.
 */
(function($){

    var close;
    var closeOn = true;
    var mask = $("<div id=\"mask\"></div>");
    var hint = $("<div id=\"hint\"></div>");

    var maskClose = function () {
        if(close){
            mask.hide();
            close();
        }
    };

    $(function () {
        $("body").append(mask);
        mask.click(maskClose);
    });
    $.extend({
        mask : function (closeFunc) {
            close = closeFunc;
            mask.show();
        },
        maskClose : maskClose,
        hint : function (msg) {
            closeOn = false;
            hint.text(msg);
            mask.append(hint);
            mask.show();
        },
        hintClose : function () {
            closeOn = true;
            mask.html("");
            mask.hide();
        }
    });

})(jQuery);