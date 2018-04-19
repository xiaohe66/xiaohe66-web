/**
 * @author  xiaohe
 * @time    17-11-12 012
 */
var REGISTER_URL = "/org/usr/register";
var CODE_IMG_URL = "/validate";
$(function(){
    var code = $("#code");
    code.find("img").click(function(){
        $(this).attr("src",CODE_IMG_URL+"?m="+Math.random());
    });
    $("#register").find(".btn").click(register);

    $("#usrName").find("input").focus(function (e) {
        //判断用户名是否存在

    });

});
function register(){
    var usrName = $("#usrName").find("input").val();
    if(usrName === undefined || usrName === ""){
        alert("请输入用户名");
        return;
    }
    var usrPwd = $("#usrPwd").find("input").val();
    if(usrPwd === undefined || usrPwd === ""){
        alert("请输入密码");
        return;
    }
    var usrPwd2 = $("#usrPwd2").find("input").val();
    if(usrPwd2 === undefined || usrPwd2 === ""){
        alert("请再次输入密码");
        return;
    }
    if(usrPwd !== usrPwd2){
        alert("两次密码输入不同");
        return;
    }
    var code = $("#code").find("input").val();
    if(code === undefined || code === ""){
        alert("请输入验证码");
        return;
    }

    //调用蒙层


    //注册
    $.post(REGISTER_URL,{
        usrName:usrName,
        usrPwd:usrPwd,
        code:code
    },function(data){
        location.href = "/index"
    },function () {
        //显示异常信息

        //1s后清除蒙层
        setTimeout(function () {

        },1000);
    });
}