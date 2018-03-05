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
<div class="t">
    <div class="t_t">
        <a href="javascript:void(0)">消息</a>
        <a href="javascript:void(0)">登录</a>
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
            <li>github</li>
            <li>留言</li>
        </ul>
        <p class="logo">xiaohe66</p>
        <div class="search">
            <%--<ul class="search_type">
                <li>百度</li>
                <li>谷歌</li>
            </ul>--%>
            <select class="search_type">
                <option value="https://www.baidu.com/s?wd=">百度</option>
                <option value="https://www.google.com/search?q=">谷歌</option>
            </select>
            <input class="inp" placeholder="搜索">
            <%--<a href="javascript:void(0);" class="btn">搜索</a>--%>
        </div>
    </div>
    <script>
        $(function () {
            var inp = $(".inp");
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
        });
    </script>
</div>