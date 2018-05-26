/**
 * @author xh
 * @time 18-05-26 026
 */
$(function () {
    var baseUrl = "/text/article/";
    var tbody = $("#article_tab").find("tbody");

    var createTr = function (data) {
        var tr = $("<tr id=\""+data.id+"\"></tr>");
        tr.append("<td><a class='name'>" + data.title + "</a></td>");
        tr.append("<td>" + data.sysCategoryName + "</td>");
        tr.append("<td>" + data.perCategoryNames + "</td>");
        tr.append("<td>" + data.createTime + "</td>");
        tr.append("<td><a class=\"editer\">编辑</a>\n<a class=\"del\">删除</a></td>");
        tr.attr("id", data.id);
        return tr;
    };

    $("#paging").paging(pages,pageNum, function (page) {
        tbody.html("");
        paging(baseUrl+"usr",function (data) {
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
            del(baseUrl+id,function () {
                tr.remove();
            });
        }
    });

    $(document).on("click","#article_tab .name",function () {
        var url = baseUrl+"detail/"+$(this).parent().parent().attr("id");
        window.open(url);
    });

    $(document).on("click", ".editer", function () {
        var url = baseUrl+"editor/"+$(this).parent().parent().attr("id");
        window.open(url);
    });

});