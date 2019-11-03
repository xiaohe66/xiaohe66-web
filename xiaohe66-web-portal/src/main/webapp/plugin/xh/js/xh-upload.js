/**
 * @author xh
 * @time 19-10-15 015
 */
(function ($) {

    let maxThread = 3;
    let upload = function (file, call, isShowHint) {
        if (isShowHint) $.hint("上传初始化中……");
        calculate(file, function (md5) {

            let name = file.name;
            let index = name.lastIndexOf(".");
            if (index === -1) index = name.length;

            let fileName = name.substring(0, index);
            let extension = name.substring(index);
            let fileId;

            let uploadSuccess = function () {
                // if (isShowHint) $.hint("上传成功");
                call({
                    fileId: fileId,
                    md5: md5,
                    fileName: fileName,
                    extension: extension
                });
            };

            if (isShowHint) $.hint("查询文件状态");
            post("/comm/file/prepare", {
                md5: md5,
                mb: file.size / 1024 / 1024,
                name: name,
            }, function (data) {
                if (data.length === 0) {
                    uploadSuccess();
                    return;
                }

                fileId = data.fileId;
                let missingChunk = data.missingChunk;
                let surplus = missingChunk.length;
                console.log("upload prepare result:", data);

                if (surplus === 0) {
                    uploadSuccess();
                    return;
                }

                let uploadMsg = function () {
                    // todo : 上传过的文件也应该计算进百分比中
                    let percentage = (missingChunk.length - surplus) / data.countChunk * 100 + "";
                    percentage = percentage.substring(0, percentage.length < 2 ? percentage.length : 2);
                    if (isShowHint) $.hint("上传中:" + percentage + "%");
                };

                uploadMsg();

                let maxBChunkPer = data.maxMbChunkPer * 1024 * 1024;

                let submit = function (i) {
                    if (i >= missingChunk.length) return;

                    let chunk = missingChunk[i];
                    console.log("chunk=" + chunk);
                    //计算每一片的起始与结束位置
                    let start = (chunk - 1) * maxBChunkPer;
                    let end = Math.min(file.size, start + maxBChunkPer);
                    //构造一个表单
                    let form = new FormData();
                    form.append("file", file.slice(start, end));
                    form.append("chunk", chunk);
                    form.append("md5", md5);
                    ajax({
                        url: "/comm/file",
                        type: "post",
                        data: form,
                        cache: false,
                        processData: false,
                        contentType: false
                    }, function (msg) {
                        if (--surplus === 0) {
                            uploadSuccess();
                        } else {
                            uploadMsg();
                            submit(i + maxThread);
                        }
                    });
                };
                submit(0);
                submit(1);
                submit(2);
            });
        });
    };

    $.upload = function (isShowHint, data, call) {
        let r = $.extend({elem: '#upload', size: 1024}, data);
        r.auto = false;
        r.choose = function (obj) {
            obj.preview(function (index, file, result) {
                console.log("file", file);
                upload(file, call, isShowHint);
            });
        };
        layui.upload.render(r);
    };
    $.fn.upload = function (isShowHint, call) {
        $(this).change(function () {
            let filePath = $(this).val();
            if (filePath === "") return;
            let file = this.files[0];
            let fileSizi = (file.size / 1024).toFixed(2);
            if (fileSizi > 1048576) {
                alert("目前仅支持1G以内的文件上传");
                return;
            }
            upload(file, call, isShowHint);
        });
    };
    $.fn.initUploadBtn = function (isShowHint, call) {
        let inp = $("<input type='file'>");
        $("body").append(inp);
        $(this).click(function () {
            inp.click();
        });
        inp.change(function () {
            let filePath = $(this).val();
            if (filePath === "") return;
            let file = this.files[0];
            let fileSizi = (file.size / 1024).toFixed(2);
            if (fileSizi > 1048576) {
                alert("目前仅支持1G以内的文件上传");
                return;
            }
            upload(file, call, isShowHint);
            inp.val("");
        });
    };
})(jQuery);