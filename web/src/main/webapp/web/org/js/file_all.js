$(function () {
    var tbody = $("#file_tab").find("tbody");
    var onlyWebmaster = false;
    var html = $("tr")[1].outerHTML;
    var search = "";

    var getPaging = function (page) {
        tbody.html("");
        $.getPaging("/org/usr/file/all/"+onlyWebmaster+"/"+search,page,20,{},function (arr) {
            $.each(arr,function (i, data) {
                tbody.append(html);
                var item = tbody.find("tr:last");
                item.attr("id",data.id);

                item.find(".name").text(data.fileName);
                var tds = item.find("td");
                tds.eq(1).text(data.extension);
                tds.eq(2).text(data.createTime);
                tds.eq(3).find("a").text(data.usrName);
            });
        });
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

$(document).on("click","#file_tab .name",function () {
    var id = $(this).parent().parent().attr("id");
    window.open("/org/usr/file/download/"+id);
});
