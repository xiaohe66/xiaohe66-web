$(function () {

    var onlyWebmaster = false;
    var tbody = $("#file_tab").find("tbody");
    var html = $(".item")[0].outerHTML;
    var div = $(".c_c");
    var search = "";

    var getPaging = function (page) {
        div.find(".item").remove();
        paging("/text/article/all/"+onlyWebmaster+"/"+search,function (arr) {
            $.each(arr,function (i, data) {
                $("#paging").before(html);
                var item = div.find(".item:last");
                item.attr("id",data.id);
                item.find(".title").text(data.title);
                var spans = item.find(".u_r_d");
                spans.eq(0).text(data.usrName);
                spans.eq(1).text(data.createTime);
                spans.eq(2).text(data.sysCategoryName);
                spans.eq(3).text(data.perCategoryNames);
                item.find(".fr").find("span").text(data.count);
                item.find(".desc").html(data.text);
            });
        },page,10)
    };

    $("#search").keydown(function (e) {
        if(e.keyCode===13){
            var val = $(this).val();
            if(search !== val){
                search = val;
                getPaging(1);
            }
        }
    });

    $("#onlyWebmaster").click(function () {
        $(this).toggleClass("on");
        onlyWebmaster = !onlyWebmaster;
        getPaging(1);
    });

    $("#paging").paging(max,num,function (page) {
        getPaging(page);
    });
});

$(document).on("click",".item",function () {
   location.href = "/text/article/detail/"+$(this).attr("id");
});
$(document).on("mouseover",".item",function () {
    $(this).find(".fl").show();
    $(this).find(".u_r_d").show();
    $(this).find(".desc").show();
    $(this).find(".u_r").css("margin-left","70px");
});
$(document).on("mouseleave",".item",function () {
    $(this).find(".fl").hide();
    $(this).find(".u_r_d").hide();
    $(this).find(".desc").hide();
    $(this).find(".u_r").css("margin-left","0");
});
