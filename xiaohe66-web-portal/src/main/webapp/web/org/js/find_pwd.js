/**
 * @author xh
 * @time 18-10-24 024
 */

$(function () {
   var code = $("#code").find("img");
   var emailInp = $("#email").find("input");
   var codeInp = $("#code").find("input");

    var refreshAuthCode = function () {
        code.attr("src","/authCode/img?m="+Math.random());
    };

    code.click(refreshAuthCode);

    $("#prev").click(function () {
        $(".s2").hide();
        $(".s1").show();
        $("#submit").hide();
        $("#prev").hide();
        $("#next").css("display","inline-block");
        emailInp.attr("disabled",false);
        refreshAuthCode();
    });

    var callFunc = function (dom, msg) {
        dom.next().text(msg);
    };


    var s1 = new Validator();
    s1.add({
        dom: emailInp,
        maxLength:32,
        regex:/^[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?$/,
        call:callFunc
    });

    $("#next").click(function () {
        if (!s1.verify())return;
        $.hint("处理中……");
        post("/sec/login/pwd",{
            email:emailInp.val(),
            code:codeInp.val()
        },function (data) {
            $(".s1").hide();
            $(".s2").show();
            $("#submit").css("display","inline-block");
            $("#prev").css("display","inline-block");
            $("#next").hide();
            emailInp.attr("disabled",true);
            $.hintClose();
            refreshAuthCode();
        },function (data) {
            if(data.code === 425){
                $.maskHint("验证码错误，请重试");
            }else{
                $.maskHint("系统繁忙，请刷新后重试");
            }
            refreshAuthCode();
        });
    });

    var s2 = new Validator();
    var pwdInp = $("#password").find("input");
    var pwdInp2 = $("#password2").find("input");
    var emailCodeInp = $("#emailCode").find("input");
    s2.add({
        dom: pwdInp,
        maxLength:16,
        call:callFunc
    });

    s2.add({
        dom: pwdInp2,
        check:function (dom,val) {
            var pwd1 = $("#password").find("input").val();
            return pwd1 !== val?"2次密码不同":true;
        },
        call:callFunc
    });
    s2.add({
        dom:emailCodeInp,
        call:callFunc
    });

    $("#submit").click(function () {
        if (!s2.verify()) return;
        $.hint("处理中……");
        put("/sec/login/pwd",{
            password:pwdInp.val(),
            code:emailCodeInp.val()
        },function (data) {
            $.maskHint("修改成功",function () {
                location.href = "/index";
            });
        },function (data) {
            $.maskHint("系统繁忙，请刷新后重试");
            refreshAuthCode();
        });
    });
});