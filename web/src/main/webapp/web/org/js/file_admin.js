/**
 * Created by xh on 18-03-30 030.
 */
$(function () {
    var baseUrl = "/org/usr/file";
    var tbody = $("#file_tab").find("tbody");

    var createTr = function (data) {
        var tr = $("<tr id=\""+data.id+"\"></tr>");
        tr.append("<td><a class='name'>" + data.fileName + "</a></td>");
        tr.append("<td>" + data.extension + "</td>");
        tr.append("<td>" + data.fileSize + "</td>");
        tr.append("<td>" + data.createTime + "</td>");
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
        var url = "/org/usr/file/down/"+$(this).parent().parent().attr("id");
        window.open(url);
    });

    $(".add input").change(function () {
        var file = this.files[0];
        var fileSizi = (file.size/1024).toFixed(2);
        if(fileSizi > 10240){
            alert("目前仅支持10M以内的文件上传");
            return;
        }
        calculate(file,function (md5) {
            var formdata = new FormData();
            formdata.append("file",file);
            formdata.append("md5",md5);
            ajax({
                url: baseUrl,
                data: formdata,
                type: "post",
                cache: false,
                dataType: "json",
                contentType: false,
                processData: false,
            },function (data) {
                tbody.prepend(createTr(data));
                if(tbody.find("tr").length >= 11){
                    tbody.find("tr:last").remove();
                }
            });
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
        if(newName.length >= 20){
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