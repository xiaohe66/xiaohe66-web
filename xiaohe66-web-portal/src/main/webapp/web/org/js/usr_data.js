/**
 * Created by xh on 18-04-04 004.
 */
var baseUrl = "/org/usr";
$(function () {
   $(".down").find(".btn").click(function () {
       var signature = html2Escape($(".signature").val());
       $.hint("保存中");
       put(baseUrl,{signature:signature},function (data) {
           $.hintClose();
           $(".signature").html(signature);
       });
   });

   $(".right").find(".btn").click(function () {
        $("#file").find("input").click();
   });

   $("#file").find("input").change(function () {
        var file = this.files[0];
        var pic = $(this).val();
        var picType = pic.substring(pic.lastIndexOf("."), pic.length).toUpperCase();
        if (picType !== ".JPG" && picType !== ".PNG" && picType !== ".BMP") {
            alert("请选择jpg、png或bmp格式的图片");
            $(this).val("");
            return;
        }
        var fileSize = (file.size/1024).toFixed(2);
        if(fileSize > 2048){
            alert("最大支持2M的图片");
            return;
        }
        calculate(file,function (md5) {
            var formData = new FormData();
            formData.append("file",file);
            formData.append("md5",md5);
            $.ajax({
                url: "/org/usr/file/head",
                data: formData,
                type: "post",
                cache: false,
                dataType: "json",
                contentType: false,
                processData: false,
                success: function (data) {
                    log(data);
                    $(".right").find("img").attr("src","/org/usr/file/img/"+data.data);
                }
            });
        });
   });
});