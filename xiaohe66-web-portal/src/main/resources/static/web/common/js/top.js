$(function () {
    var inp = $(".search .inp");
    inp.keydown(function(e){
        if(e.keyCode === 13){
            var val = inp.val();
            if(val === undefined || val.length === 0){
                alert("请输入搜索内容");
                return;
            }
            window.open($(".search_type").val()+inp.val());
        }
    });
    var top = $(".t");

    var t,p,lock = true;
    $(window).scroll(function(e){
        if(!lock){
            return;
        }
        p = $(this).scrollTop();
        lock = false;
        $(".t").animate({
            "top": t<=p?-100:0+"px"
        },100,function () {
            t = p;
            lock = true;
        });
    });

    var login = function () {
        var loginName = $("#loginName").val();
        var usrPwd = $("#usrPwd").val();

        post("/sec/login",{
            loginName:loginName,
            usrPwd:usrPwd
        },function (data) {
           location.reload();
        },function (data) {
            $("#login_body p").text(data.msg);
        });
    };

    $(".login a").click(login);

    $("#usrPwd").keyup(function (e) {
        if(e.keyCode === 13){
            login();
        }
    });

});

function logout() {
    del("/sec/login",function (data) {
        location.reload();
    });
}

function showLogin() {
    $("#login_body").show();
    $.mask(function () {
       $("#login_body").hide();
    });
    $("#loginName").focus();
}