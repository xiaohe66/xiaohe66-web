/**
 *
 *
 * @author xh
 * @date 18-03-28 028
 */
$(function () {
    var url = "/text/category";
    $(document).on("click",".del",function () {
        if(confirm("确定要删除吗")){
            var tr = $(this).parent().parent();
            var id = parseInt(tr.attr("categoryId"));

            $.hint("删除中，请稍候...");
            $.del(url+"/"+id,function (data) {
                $.hintClose();
                tr.remove();
                showHideAdd();
            });
        }
    });

    $(document).on("click",".rename",function () {
        var tr = $(this).parent().parent();
        if(tr.hasClass("on")){
            return;
        }
        tr.addClass("on");
        var td = tr.find(".name");
        var name = td.text();
        td.attr("name",name);
        var inp = $("<input placeholder=\"输入新的分类名\" value=\""+name+"\">");
        td.html(inp);
        td.append("<a href=\"javascript:void(0);\" class=\"save\">保存</a>\n" +
            "<a href=\"javascript:void(0);\" class=\"cancel\">取消</a>");
        inp.focus();
    });

    $(document).on("click",".save",function () {
        save($(this));
    });
    $(document).on("keydown","#category_tab input",function (e) {
        if(e.keyCode === 13){
            save($(this));
        }
    });

    function save($this) {
        var td = $this.parent();
        var newName = td.find("input").val();
        if(newName === undefined || newName.length===0){
            alert("请输入名称");
            return;
        }
        var id = td.parent().attr("categoryId");
        //保存新分类名
        $.hint("保存中，请稍候...");
        $.put(url,{
            categoryName:newName,
            id:id
        },function (data) {
            //更新ui
            $.hintClose();
            td.html(newName);
            td.parent().removeClass("on");
        });
    }

    $(document).on("click",".cancel",function () {
        var td = $(this).parent();
        td.html(td.attr("name"));
        td.parent().removeClass("on");
    });

    $(".add a").click(function () {
        var inp = $(".add input");
        var val = inp.val();
        if(val === undefined || val.length === 0){
            return;
        }

        $.hint("保存中，请稍候...");
        $.post(url,{categoryName:val},function (data) {
            var tbody = $("#category_tab").find("tbody");
            var tr = $("<tr></tr>");
            tr.append("<td class='name'>"+val+"</td>");
            tr.append("<td>"+data.createTime+"</td>");
            tr.append("<td><a class=\"rename\">重命名</a>\n<a class=\"del\">删除</a></td>");

            tr.attr("categoryId",data.id);

            inp.val("");
            $.hintClose();
            tbody.append(tr);
            showHideAdd();
        });

    });

});

function showHideAdd() {
    if($("#category_tab").find("tbody").find("tr").length >= 10){
        $(".add").hide();
    }else{
        $(".add").show();
    }
}