/**
 * @author xh
 * @time 18-05-26 026
 */
$(function () {
    var baseUrl = "/text/article/";
    var tbody = $("#article_tab").find("tbody");

    $("#articleManagement").addClass("active");

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

    var secretLevel;
    var showData = function (page) {
        tbody.html("");
        paging(baseUrl+"admin/"+(secretLevel!==undefined?secretLevel:""),function (data) {
            $.each(data,function (i, item) {
                tbody.append(createTr(item));
            });
        },page,10);
    };
    $("#paging").paging(pages,pageNum,showData);

    $(".c_t .tab li").click(function () {
        $(".c_t .tab li").removeClass("active");
        $(this).addClass("active");

        secretLevel = $(this).attr("secretLevel");
        var first = $("#paging").find("div:first");
        if(first.length !== 0){
            first.click();
        }else{
            showData(1);
        }
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
        location.href = baseUrl+"detail/"+$(this).parent().parent().attr("id");
    });

    $(document).on("click", ".editer", function () {
        location.href = baseUrl+"editor/"+$(this).parent().parent().attr("id");
    });

});