/**
 * Created by xh on 18-04-04 004.
 */
var baseUrl = "/org/usr";
$(function () {
   $(".down").find(".btn").click(function () {
       var signature = $.html2Escape($(".signature").val());
       $.hint("保存中");
       $.put(baseUrl,{signature:signature},function (data) {
           $.hintClose();
           $(".signature").html(signature);
       });
   }); 
});