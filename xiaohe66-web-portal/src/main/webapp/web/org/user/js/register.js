/**
 * @author  xiaohe
 * @time    17-11-12 012
 */
$(function () {
    var code = $("#code");

    var refreshAuthCode = function () {
        $(this).attr("src", "/authCode/img?m=" + Math.random());
    };

    code.find("img").click(refreshAuthCode);

    var usrNameInp = $("#usrName").find("input");
    var emailInp = $("#email").find("input");
    var pwdInp = $("#password").find("input");
    var codeInp = code.find("input");

    var validator = new Validator();
    var callFunc = function (dom, msg) {
        dom.next().text(msg);
    };
    validator.add({
        dom: usrNameInp,
        maxLength: 16,
        regex: /^([\u4e00-\u9fa5]|[a-z]|[A-Z]|[0-9]|_){1,16}$/,
        check: function (dom, val) {
            var $t = this;
            if (this.prev === val) {
                return $t.ret;
            }
            ajax({url: "/org/user/name/" + val, async: false, dataType: "json"}, function (data) {
                $t.ret = data ? "用户名已存在" : true;
            });
            this.prev = val;
            return $t.ret;
        },
        call: callFunc
    });

    validator.add({
        dom: emailInp,
        maxLength: 32,
        regex: /^[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?$/,
        check: function (dom, val) {
            var $t = this;
            if (this.prev === val) {
                return $t.ret;
            }
            ajax({url: "/org/user/email?email=" + val, async: false, dataType: "json"}, function (data) {
                $t.ret = data ? "邮箱已被注册" : true;
            });
            this.prev = val;
            return $t.ret;
        },
        call: function (dom, msg) {
            dom.next().text(msg);
        }
    });

    validator.add({
        dom: pwdInp,
        maxLength: 16,
        call: callFunc
    });

    validator.add({
        dom: $("#password2").find("input"),
        check: function (dom, val) {
            var pwd1 = $("#password").find("input").val();
            return pwd1 !== val ? "2次密码不同" : true;
        },
        call: callFunc
    });

    validator.add({
        dom: codeInp,
        maxLength: 4,
        check: function (dom, val) {
            var $t = this;
            if (this.prev === val) {
                return $t.ret;
            }
            ajax({url: "/authCode/" + val, async: false, dataType: "json"}, function (data) {
                $t.ret = data ? true : "验证码错误";
            });
            this.prev = val;
            return $t.ret;
        },
        call: function (dom, msg) {
            code.find("span").text(msg);
        }
    });

    $("#register").find(".btn").click(function () {
        if (validator.verify()) {

            $.hint("操作中，请稍候");
            post("/sec/login/register", {
                userName: usrNameInp.val(),
                email: emailInp.val(),
                userPwd: pwdInp.val(),
                code: codeInp.val(),
                signature: $("#signature").find("textarea").val()
            }, function (data) {
                if (data) {
                    $.maskHint("注册邮件已发送至您的邮箱", function () {
                        location.href = "/index";
                    });
                }
            }, function (data) {
                alert("出现未知错误，请刷新后重试");
                setTimeout(function () {
                    $.hintClose();
                }, 1000);
            });
        }
    });

});