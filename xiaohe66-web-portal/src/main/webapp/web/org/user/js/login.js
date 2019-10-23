$(function () {


    let loginName = $("#loginName input");
    let pwdInp = $("#password input");

    pwdInp.keydown(function (e) {
        if (e.keyCode === 13) {
            $("#loginBtn").click();
        }
    });

    $("#find div").click(function () {
        location.href = "/org/user/pwd";
    });


    let validator = new Validator();
    let callFunc = function (dom, msg) {
        dom.next().text(msg);
    };

    validator.add({
        dom: loginName,
        maxLength: 16,
        regex: /^(([\u4e00-\u9fa5]|[a-z]|[A-Z]|[0-9]|_){1,16})|([\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?)$/,
        call: callFunc
    });

    validator.add({
        dom: pwdInp,
        maxLength: 16,
        call: callFunc
    });

    $(".btn").click(function () {
        if (validator.verify()) {
            $.hint("登录中，请稍候");
            post("/sec/login", {
                loginName: loginName.val(),
                userPwd: pwdInp.val()
            }, function (data) {
                location.href = '/index';
            }, function (data) {
                alert(err.data);
            });
        }
    });
});