/**
 * @author xh
 * @time 19-10-15 015
 */
(function ($) {
    $.fn.upload = function (call) {
        $(this).change(function () {
            var file = this.files[0];
            var pic = $(this).val();
            var picType = pic.substring(pic.lastIndexOf("."), pic.length).toUpperCase();
            if (picType !== ".JPG" && picType !== ".PNG" && picType !== ".BMP") {
                alert("请选择jpg、png或bmp格式的图片");
                $(this).val("");
                return;
            }
            var fileSize = (file.size / 1024).toFixed(2);
            if (fileSize > 2048) {
                alert("最大支持2M的图片");
                return;
            }
            calculate(file, function (md5) {
                var formData = new FormData();
                formData.append("file", file);
                formData.append("md5", md5);
                $.ajax({
                    url: "/comm/file/img",
                    data: formData,
                    type: "post",
                    cache: false,
                    dataType: "json",
                    contentType: false,
                    processData: false,
                    success: function (data) {
                        log(data);
                        call("/comm/file/img/" + data.data,data.data);
                    }
                });
            });
        });
    }
})(jQuery);