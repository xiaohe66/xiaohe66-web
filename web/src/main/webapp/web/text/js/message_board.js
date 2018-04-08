/**
 *
 *
 * @author xh
 * @date 18-03-15 015
 */
$(function () {
    var div = $(".content");
    var itemHtml = div.find(".item")[0].outerHTML;

    var maxPage = Math.ceil(parseInt($("#size").val())/5);
    $("#paging").paging(maxPage,1,function (page) {
        $.getPaging("/text/messageBoard",page,10,{
            usrId:$("#usrId").val()
        },function (arr) {
            div.html("");
            $.each(arr,function (i, data) {
                div.append(itemHtml);
                var item = div.find(".item:last");
                var span = item.find(".u_r_d").find("span");
                span.eq(0).text(data.usrName);
                span.eq(1).text(data.createTime);
                span.eq(2).text(data.id+"楼");
                item.find(".desc").html(data.msg);
            });
        })
    });

    $(".msg .btn").click(function () {
        var edit = $(".edit textarea");
        var val = html2Escape(edit.val());
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
        $.post("/text/messageBoard",{
            usrId:$("#usrId").val(),
            msg:val
        },function (data) {
            location.reload();
        });
    });


});
function html2Escape(sHtml) {
    return sHtml.replace(/[<>&"]/g,function(c){
        return {'<':'&lt;','>':'&gt;','&':'&amp;','"':'&quot;'}[c];
    });
}