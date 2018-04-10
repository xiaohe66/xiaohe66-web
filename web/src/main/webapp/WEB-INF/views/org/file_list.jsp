<%--
  Created by IntelliJ IDEA.
  User: xh
  Date: 18-03-21 021
  Time: 11:57 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link type="text/css" rel="stylesheet" href="/css/xh/xh-paging.css"/>
<link type="text/css" rel="stylesheet" href="/web/org/css/file_list.css"/>
<script type="text/javascript" src="/js/xh/xh-paging.js"></script>
<script type="text/javascript" src="/web/org/js/file_list.js"></script>
<div class="fr add">
    <input type="file">
    <a class="btn">上传</a>
</div>
<p>文件管理</p>
<div class="c_c">
    <table id="file_tab" border="0"cellpadding="0" cellspacing="0">
        <colgroup>
            <col>
            <col width="100px">
            <col width="200px">
            <col width="100px">
        </colgroup>
        <thead>
        <tr>
            <th>文件名</th>
            <th>文件类型</th>
            <th>上传日期</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <tr usrFileId="1">
            <td><a  class="name" href="javascript:void(0);">Intellij</a></td>
            <td>.jpg</td>
            <td>2018-03-22 18:23:23</td>
            <td>
                <a href="javascript:void(0);" class="rename">重命名</a>
                <a href="javascript:void(0);" class="del">删除</a>
            </td>
        </tr>
        </tbody>
    </table>
</div>
<div id="paging"></div>