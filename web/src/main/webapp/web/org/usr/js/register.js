/**
 * @author  xiaohe
 * @time    17-11-12 012
 */
var REGISTER_URL = "/org/usr/register";
var CODE_IMG_URL = "/validate";
$(function(){

    $("#codeImg").click(function(){
        $(this).attr("src",CODE_IMG_URL+"?m="+Math.random());
    });
});
function register(){
    var usrName = $("#usrName").val();
    if(usrName == undefined || usrName == ""){
        alert("请输入用户名");
        return;
    }
    var usrPwd = $("#usrPwd").val();
    if(usrPwd == undefined || usrPwd == ""){
        alert("请输入密码");
        return;
    }
    var usrPwd2 = $("#usrPwd2").val();
    if(usrPwd2 == undefined || usrPwd2 == ""){
        alert("请输入密码");
        return;
    }
    if(usrPwd != usrPwd2){
        alert("两次密码输入不同");
        return;
    }
    var code = $("#code").val();
    if(code == undefined || code == ""){
        alert("请输入验证码");
        return;
    }

    $.post(REGISTER_URL,{
        usrName:usrName,
        usrPwd:usrPwd,
        code:code
    },function(data){
        window.location.href = "/index"
    },function(err){
        alert(err.data);
    });
}