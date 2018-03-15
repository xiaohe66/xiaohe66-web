/**
 * Created by xh on 18-03-08 008.
 */
(function($){

    var close;
    var mask = $("<div id=\"mask\"></div>");

    $(function () {
        $("body").append(mask);
        mask.click(function () {
            mask.hide();
            close();
        });
    });
    $.extend({
        mask : function (closeFunc) {
            close = closeFunc;
            mask.show();
        }
    });

})(jQuery);