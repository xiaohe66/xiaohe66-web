/**
 * @author  xiaohe
 * @time    17-11-12 012
 */
var REGISTER_URL = "/usr/register";
var CODE_IMG_URL = "/authCode/";
var IS_EXIST_URL = "/org/usr/exist/";
$(function(){
    var code = $("#code");
    code.find("img").click(function(){
        $(this).attr("src",CODE_IMG_URL+"img?m="+Math.random());
    });
    $("#register").find(".btn").click(register);

    $("#usrName").find("input").blur(function () {
        var $this = $(this);
        var name = $this.val();
        if(name.length === 0){
            return;
        }
        get(IS_EXIST_URL+name,function (data) {
            $this.next().text(data?"用户名重复":"");
        });
    });

    $("#password2,#password").find("input").blur(function () {
        var pwd2Inp = $("#password2").find("input");
        var pwd1 = $("#password").find("input").val();
        var pwd2 = pwd2Inp.val();
        if(pwd1.length === 0){
            return;
        }
        pwd2Inp.next().text(pwd1 !== pwd2?"2次不同":"");
    });

    var lastVal;
    code.find("input").blur(function () {
        var val = $(this).val();
        var span = code.find("span");
        if(val.length !== 4 || val === lastVal){
            if(val.length !== 4){
                span.text("验证错误");
            }
            return;
        }
        get("/authCode/"+val,function (data) {
            lastVal = val;
            span.text(data?"":"验证错误");
        });
    });

});
function register(){
    var usrName = $("#usrName").find("input").val();
    if(usrName === undefined || usrName === ""){
        alert("请输入用户名");
        return;
    }
    var usrPwd = $("#password").find("input").val();
    if(usrPwd === undefined || usrPwd === ""){
        alert("请输入密码");
        return;
    }
    var usrPwd2 = $("#password2").find("input").val();
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

    var email = $("#email").find("input").val();
    if(email === undefined || email === ""){
        alert("email");
        return;
    }

    $.hint("操作中，请稍候");
    post(REGISTER_URL,{
        usrName:usrName,
        usrPwd:usrPwd,
        code:code,
        email:email,
        signature:$("#signature").find("textarea").val()
    },function(data){
        if(data){
            $.maskHint("注册邮件已发送至您的邮箱",function () {
                location.href = "/index"
            });
        }
    },function (data) {
        alert(data.data);
        setTimeout(function () {
            $.hintClose();
        },1000);
    });
}