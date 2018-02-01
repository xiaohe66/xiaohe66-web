/**
 * @author  xiaohe
 * @time    17-11-12 012
 */
var LOGIN_URL = "/org/usr/login";
$(function(){
    $.log("hello");

    $("#usrPwd").keydown(function(e){
        if(e.keyCode === 13){
            $("#loginBtn").click();
        }
    });

    $("#loginBtn").click(function(){
        $.post(LOGIN_URL,{
            usrName:$("#usrName").val(),
            usrPwd:$("#usrPwd").val()
        },function(data){
            window.location.href = '/index';
        },function(err){
            alert(err.data);
        });
    });

    $(".title").click(function(){
        var val = $(this).val();
        window.location.href = "/text/article/detail?id="+val;
    });

});