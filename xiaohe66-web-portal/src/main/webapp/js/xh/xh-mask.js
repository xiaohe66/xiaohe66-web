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
    });
    $.extend({
        mask : function (closeFunc) {
            close = closeFunc;
            mask.html("");
            mask.show();
        },
        maskHint : function (msg,closeFunc) {
            close = closeFunc;
            hint.text(msg);
            mask.html(hint);
            mask.show();
        },
        maskHtml : function (html,closeFunc) {
            close = closeFunc;
            mask.html(html);
            mask.show();
        },
        maskClose : maskClose,
        hint : function (msg) {
            closeOn = false;
            hint.text(msg);
            mask.html(hint);
            mask.show();
        },
        hintClose : function () {
            closeOn = true;
            mask.hide();
        }
    });

})(jQuery);