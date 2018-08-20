/**
 *
 *
 * @author xh
 * @date 18-03-15 015
 */
$(function () {
    var div = $(".content");
    var itemHtml = div.find(".item")[0].outerHTML;

    $("#paging").paging(parseInt($("#size").val()),1,function (page) {
        paging("/text/messageBoard/"+$("#usrId").val(),function (arr) {
            div.html("");
            $.each(arr,function (i, data) {
                div.append(itemHtml);
                var item = div.find(".item:last");
                item.find(".head_img").attr("src","/org/usr/file/img/"+data.imgFileId);
                var span = item.find(".u_r_d").find("span");
                span.eq(0).text(data.usrName);
                span.eq(1).text(data.createTime);
                span.eq(2).text(data.id+"楼");
                item.find(".desc").html(data.msg);
            });
        },page,10);
    });

    $(".msg .btn").click(function () {
        var edit = $(".edit textarea");
        var val = $.html2Escape(edit.val());
        if($.isEmpty(val)){
            alert("请填写留言内容");
            return;
        }
        if(val.length>1000){
            alert("留言不能超过1000字");
        }

        if($(this).hasClass("on")){
            return;
        }

        $(this).addClass("on");
        post("/text/messageBoard",{
            usrId:$("#usrId").val(),
            msg:val
        },function (data) {
            location.reload();
        });
    })
});