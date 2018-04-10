/**
 * Created by xh on 18-03-30 030.
 */
$(function () {
    $("#paging").paging(2, 1, function (page) {
        console.log(page);
    });

    $(document).on("click", ".del", function () {
        if (confirm("确定要删除吗")) {
            var tr = $(this).parent().parent();
            var id = tr.attr("categoryId");
            console.log(id);
            tr.remove();
        }
    });

    $(".add input").change(function () {
        var val = $(this).val();
        //上传的逻辑

        //成功到的逻辑
        var data = {categoryName:"文件名",type:".rar",createTime:"2018-03-29 17:58:44"};
        var tbody = $("#file_tab").find("tbody");
        var tr = $("<tr></tr>");
        tr.append("<td><a class='name'>" + data.categoryName + "</a></td>");
        tr.append("<td>" + data.type + "</td>");
        tr.append("<td>" + data.createTime + "</td>");
        tr.append("<td><a href=\"javascript:void(0);\" class=\"rename\">重命名</a>\n" +
            "<a href=\"javascript:void(0);\" class=\"del\">删除</a></td>");

        tr.attr("usrFileId", 1);
        tbody.prepend(tr);

        if(tbody.find("tr").length >= 12){
            tbody.find("tr:last").remove();
        }
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
        var td = tr.find(".name");
        var name = td.text();
        td.attr("name", name);
        var inp = $("<input placeholder=\"输入新的分类名\" value=\"" + name + "\">");
        td.html(inp);
        td.append("<a href=\"javascript:void(0);\" class=\"save\">保存</a>");
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
        //保存新分类名

        //更新ui
        td.html(newName);
        td.parent().removeClass("on");
    });

    $(document).on("click", ".cancel", function () {
        var td = $(this).parent();
        td.html(td.attr("name"));
        td.parent().removeClass("on");
    });

});