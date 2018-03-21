/**
 * Created by xh on 18-03-08 008.
 */
(function($){

    var close;
    var mask = $("<div id=\"mask\"></div>");

    var maskClose = function () {
        mask.hide();
        close();
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
        maskClose : maskClose
    });

})(jQuery);