/**
 *
 *
 * @author xh
 * @date 18-03-28 028
 */
$(function () {

    /*$("#paging").paging(2,1,function (page) {
        console.log(page);
    });*/
    $(".del").click(function () {
        if(confirm("确定要删除吗")){
            var tr = $(this).parent().parent();
            var id = tr.attr("categoryId");
            console.log(id);
            tr.remove();
            showHideAdd();
        }
    });

    $(".rename").click(function () {
        var td = $(this).parent().parent().find(".name");
        var name = td.text();
        td.attr("name",name);
        td.html("<input placeholder=\"输入新的分类名\" value=\""+name+"\">");
        td.append("<a href=\"javascript:void(0);\" class=\"save\">保存</a>");
        td.append("<a href=\"javascript:void(0);\" class=\"cancel\">取消</a>");
    });

    $(document).on("click",".save",function () {
        var td = $(this).parent();
        var newName = td.find("input").val();
        if(newName === undefined || newName.length===0){
            alert("请输入名称");
            return;
        }
        //保存新分类名

        //更新ui
        td.html(newName);
    });

    $(document).on("click",".cancel",function () {
        var td = $(this).parent();
        td.html(td.attr("name"));
    });

    $(".add a").click(function () {
        var inp = $(".add input");
        var val = inp.val();
        if(val === undefined || val.length === 0){
            return;
        }
        var tbody = $("#category_tab").find("tbody");
        var tr = $("<tr></tr>");
        tr.append("<td>"+val+"</td>");
        tr.append("<td>"+"2018-03-29 17:58:44"+"</td>");
        tr.append("<td><a href=\"javascript:void(0);\" class=\"rename\">重命名</a>" +
            "<a href=\"javascript:void(0);\" class=\"del\">删除</a></td>");

        inp.val("");
        tbody.append(tr);
        showHideAdd();
    });

});

function showHideAdd() {
    if($("#category_tab").find("tbody").find("tr").length >= 9){
        $(".add").hide();
    }else{
        $(".add").show();
    }
}