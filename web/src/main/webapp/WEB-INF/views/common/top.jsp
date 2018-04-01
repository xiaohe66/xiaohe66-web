<%--
  Created by IntelliJ IDEA.
  User: xiaohe
  Date: 17-11-12 012
  Time: 0:13 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="login_body">
    <ul>
        <li>
            <span>账号</span>
            <input id="usrName" placeholder="账号">
        </li>
        <li>
            <span>密码</span>
            <input id="usrPwd" type="password" placeholder="密码">
        </li>
        <li  class="verify">
            <span>验证码</span>
            <input id="authCode" placeholder="验证码">
            <img src="">
        </li>
        <li class="login">
            <a class="btn">登录</a>
        </li>
    </ul>
</div>
<div class="t">
    <div class="t_t">
        <shiro:guest>
            <a href="javascript:showLogin();">登录</a>
            |
            <a href="/org/usr/register">注册</a>
        </shiro:guest>
        <shiro:authenticated>
            <a class="usrName" style="margin-right:20px;">${usr.usrName}</a>
            <a href="/text/article/add">写文章</a>
            |
            <a href="javascript:logout();">注销</a>
        </shiro:authenticated>
    </div>
    <div class="t_c">
        <div class="tab">
            <a>个人中心</a>
            <a>文章</a>
            <%--<li>github</li>--%>
            <a href="/text/messageBoard/index">留言</a>
            <a href="/about">关于</a>
        </div>
        <p class="logo"><a href="/">xiaohe66</a></p>
        <div class="search">
            <select class="search_type">
                <option value="https://www.baidu.com/s?wd=">百度</option>
                <option value="https://www.google.com/search?q=">谷歌</option>
            </select>
            <input class="inp" placeholder="搜索">
        </div>
    </div>
    <script>
        $(function () {
            var inp = $(".search .inp");
            inp.blur(function () {
                inp.attr("val",inp.val());
                inp.val("");
                $(".t_c .tab").show();
                inp.width(400);
            });
            inp.focus(function () {
                inp.val(inp.attr("val"));
                $(".t_c .tab").hide();
                inp.width(800);
            });
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
                var usrName = $("#usrName").val();
                var usrPwd = $("#usrPwd").val();

                $.post("/org/usr/login",{
                    usrName:usrName,
                    usrPwd:usrPwd
                },function (data) {
                   location.reload();
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
            $.del("/org/usr/login",{},function (data) {
                location.reload();
            });
        }
        
        function showLogin() {
            $("#login_body").show();
            $.mask(function () {
               $("#login_body").hide();
            });
            $("#usrName").focus();
        }
    </script>
</div>