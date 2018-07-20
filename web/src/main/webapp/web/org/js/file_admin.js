/**
 * Created by xh on 18-03-30 030.
 */
var chunkMaxMb = 16;
var chunkMaxB = chunkMaxMb * 1024 * 1024;
var maxThread = 3;
$(function () {
    var baseUrl = "/org/usr/file";
    var tbody = $("#file_tab").find("tbody");

    var createTr = function (data) {
        var tr = $("<tr id=\""+data.id+"\"></tr>");
        tr.append("<td><a class='name'>" + data.fileName + "</a></td>");
        tr.append("<td>" + data.extension + "</td>");
        tr.append("<td>" + data.fileSize + "</td>");
        tr.append("<td>" + data.createTime + "</td>");
        tr.append("<td>" + (data.isFinish ? "是":"否") + "</td>");
        tr.append("<td><a class=\"rename\">重命名</a>\n<a class=\"del\">删除</a></td>");
        tr.attr("id", data.id);
        return tr;
    };

    $("#paging").paging(pages,pageNum, function (page) {
        tbody.html("");
        paging(baseUrl,function (data) {
            console.log(data);
            $.each(data,function (i, item) {
                tbody.append(createTr(item));
            });
        },page,10);
    });

    $(document).on("click", ".del", function () {
        if (confirm("确定要删除吗")) {
            var tr = $(this).parent().parent();
            var id = tr.attr("id");
            del(baseUrl+"/"+id,function () {
                tr.remove();
            });
        }
    });

    $(document).on("click","#file_tab .name",function () {
        window.open("/org/usr/file/download/"+$(this).parent().parent().attr("id"));
    });

    $(".add input").change(function () {
        var filePath = $(this).val();
        if(filePath==="")return;
        var file = this.files[0];
        var fileSizi = (file.size/1024).toFixed(2);
        if(fileSizi > 1048576){
            alert("目前仅支持1G以内的文件上传");
            return;
        }
        $.hint("上传初始化中……");
        calculate(file,function (md5) {
            var uploadSuccess = function () {
                $.hint("上传成功，3秒后刷新页面");
                setTimeout(function () {
                    location.reload();
                },3000);
            };


            var upload = function (data) {
                if(data.length===0){
                    uploadSuccess();
                    return;
                }

                var surplus = data.length;
                log(data);

                var uploadMsg = function () {
                    if(surplus === 1){
                        $.hint("上传中：100%");
                    }else{
                        var percentage = (data.length-surplus+1)/data.length*100+"";
                        percentage = percentage.substring(0,percentage.length<2?percentage.length:2);
                        $.hint("上传中:"+percentage+"%");
                    }
                };

                uploadMsg();
                var submit = function (i) {
                    if(i>=data.length)return;

                    var chunk = data[i];
                    console.log("chunk="+chunk);
                    //计算每一片的起始与结束位置
                    var start = (chunk-1) * chunkMaxB;
                    var end = Math.min(file.size, start + chunkMaxB);
                    //构造一个表单
                    var form = new FormData();
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
                    },function (msg) {
                        if(--surplus === 0){
                            uploadSuccess();
                        }else{
                            uploadMsg();
                            submit(i+maxThread);
                        }
                    });
                };
                submit(0);
                submit(1);
                submit(2);
            };

            var name = file.name;
            var index = name.lastIndexOf(".");
            if(index===-1)index = name.length;
            var fileName = name.substring(0,index);
            var extension = name.substring(index);
            $.hint("查询文件状态");
            post("/org/usr/file",{md5:md5,mb:file.size/1024/1024,fileName:fileName,extension:extension},upload);
        });
    });
    
    $(".add a").click(function () {
        $(".add input").click();
    });

    $(document).on("click", ".rename", function () {
        var tr = $(this).parent().parent();
        if (tr.hasClass("on")) {
            return;
        }
        tr.addClass("on");
        var td = tr.find("td:first");
        var name = td.text();
        td.attr("name", name);
        var inp = $("<input placeholder=\"输入新的分类名\" value=\"" + name + "\">");
        td.html(inp);
        td.append("<a href=\"javascript:void(0);\" class=\"save\">保存</a>\n");
        td.append("<a href=\"javascript:void(0);\" class=\"cancel\">取消</a>");
        inp.focus();
    });

    $(document).on("click", ".save", function () {
        var td = $(this).parent();
        var newName = td.find("input").val();
        if (newName === undefined || newName.length === 0) {
            alert("请输入名称");
            return;
        }
        if(newName.length > 20){
            alert("不能超过20个字符");
            return;
        }
        var id = td.parent().attr("id");
        $.hint("保存中，请稍候");
        put(baseUrl+"/"+id,{fileName:newName},function (data) {
            td.html("<a class=\"name\">"+newName+"</a>");
            td.parent().removeClass("on");
            $.hintClose();
        });
    });

    $(document).on("click", ".cancel", function () {
        var td = $(this).parent();
        td.html("<a class=\"name\">"+td.attr("name")+"</a>");
        td.parent().removeClass("on");
    });

});