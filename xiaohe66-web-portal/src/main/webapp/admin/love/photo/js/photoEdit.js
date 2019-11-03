$(function () {

    let form = layui.form
        , layer = layui.layer;

    form.verify({
        photoName: function (value, dom) {
            if (value && value.length > 10) {
                return "名称不能超过15个字";
            }
        },
        one: function (value, dom) {
            if (!value) {
                return "第一段话不能为空";
            }
        },
        word: function (value, dom) {
            if (value && value.length > 100) {
                return "一段话不能超过100字";
            }
        },
        fileId: function (value, dom) {
            if (!value) {
                return "必须上传图片";
            }
        },
        sort: function (value, dom) {
            if (value > 100 || value < 0) {
                return "必须在0-100之间";
            }
        }
    });

    let saveLock = true;
    form.on("submit(submit)", function (data) {
        if(!saveLock)return;
        saveLock = false;
        console.log("data", data.field);
        delete data.field.file;
        post("/love/photo",data.field,function (data) {
            layer.msg("保存成功");
            saveLock = true;
        });
        return false;
    });

    let $upload = $("#upload");
    $.upload(false, {
        accept: "images"
        , acceptMime: 'image/*'
    }, function (data) {
        let imgUrl = commonFileImgUrl(data.fileId);
        let img = "<img src='" + imgUrl + "' class='photo' alt='照片'>";
        $upload.html(img);
        $upload.css("padding", "10px");
        $("#fileId").val(data.fileId);
    });

});