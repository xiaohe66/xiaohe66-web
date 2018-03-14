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
            <input placeholder="账号">
        </li>
        <li>
            <span>密码</span>
            <input placeholder="密码">
        </li>
        <li  class="verify">
            <span>验证码</span>
            <input placeholder="验证码">
            <img src="">
        </li>
        <li class="login">
            <a class="btn">登录</a>
        </li>
    </ul>
</div>
<div class="t">
    <div class="t_t">
        <a href="/editor">写文章</a>
        <a href="javascript:void(0)">小何</a>
        <a href="javascript:showLogin()">登录</a>
        <a href="javascript:void(0);">注册</a>
        <%--<span class="portrait"></span>--%>

        <%--<a href="/index" class="home">首页</a>--%>
        <%--<shiro:guest>
            <a href="/org/usr/index">登录</a>
            |
            <a href="/org/usr/register">注册</a>
        </shiro:guest>
        <shiro:authenticated>
            <a class="usrName" style="margin-right:20px;">${usr.usrName}</a>
            <a href="/text/article/add">写文章</a>
            |
            <a href="javascript:logout();">注销</a>
        </shiro:authenticated>--%>
    </div>
    <div class="t_c">
        <ul class="tab">
            <li>个人中心</li>
            <li>文章</li>
            <%--<li>github</li>--%>
            <li>留言</li>
        </ul>
        <p class="logo"><a href="/index">xiaohe66</a></p>
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
        });
        
        function showLogin() {
            $("#login_body").show();
            $.mask(function () {
               $("#login_body").hide();
            });
        }
    </script>
</div>