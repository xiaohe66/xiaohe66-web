/**
 * @author  xiaohe
 * @time    17-11-12 012
 */

var URL = "/text/article";
var DETAIL_URL = "/text/article/detail/";
var editor;
var id;
$(function(){
    var E = window.wangEditor;
    editor = new E(".tool",".editor");
    editor.customConfig.zIndex = 0;
    editor.customConfig.customUploadImg = function (files, insert) {
        var fileSize = (files[0].size/1024).toFixed(2);
        if(fileSize > 1024){
            alert("目前仅支持1M以内的文件上传");
            return;
        }
        calculate(files[0],function (md5) {
            var formData = new FormData();
            formData.append("file",files[0]);
            formData.append("md5",md5);
            $.ajax({
                url: "/org/usr/file",
                data: formData,
                type: "post",
                cache: false,
                dataType: "json",
                contentType: false,
                processData: false,
                success: function (data) {
                    insert("/org/usr/file/img/"+data.data.id);
                }
            });
        });
    };
    editor.customConfig.menus = [
        'head',  // 标题
        'bold',  // 粗体
        'fontSize',  // 字号
        'fontName',  // 字体
        'italic',  // 斜体
        'underline',  // 下划线
        'strikeThrough',  // 删除线
        'foreColor',  // 文字颜色
        'backColor',  // 背景颜色
        'link',  // 插入链接
        'list',  // 列表
        'justify',  // 对齐方式
        // 'quote',  // 引用
        // 'emoticon',  // 表情
        'image',  // 插入图片
        'table',  // 表格
        // 'video',  // 插入视频
        'code',  // 插入代码
        'undo',  // 撤销
        'redo'  // 重复
    ];
    editor.create();

    $(document).on("click",".close",function () {
        $(this).parent().remove();
    });

    id = $("#id").val();
    if(id !== undefined){
        $("#sysCategory").find("input[value='"+$("#sysCategoryId").val()+"']").attr("checked","checked");
        var perCategoryIds = $("#perCategoryIds").val();
        $.each(perCategoryIds.split(","),function (i, data) {
            $("#perCategory").find("input[value='"+data+"']").attr("checked","checked");
        });

    }else{
        $("#sysCategory").find("input:first").attr("checked","checked");
    }

    $(".addCategory").click(addCategory);

    $(".w-e-text").css({
        "overflow-y":"hidden",
        "height":"auto"
    });
});

function publish() {

    var title = $(".title").val();
    if($.isEmpty(title)){
        alert("你忘了写标题哟!");
        return;
    }
    var text = editor.txt.html();
    if($.isEmpty(title)){
        alert("写点东西吧");
        return;
    }
    var sysCategory = $("#sysCategory").find("input:checked").val();
    var perCategory = [];
    $.each($("#perCategory").find("input:checked"),function(i,data){
        perCategory.push(parseInt($(data).val()));
    });

    var data = {
        title:title,
        text:text,
        perCategoryIds:perCategory,
        sysCategoryId:sysCategory,
        id:id
    };

    var okFunc = function(data){
        alert("发表成功");
        location.href = DETAIL_URL +data;
    };

    var errFunc = function (err) {
        alert(err.data);
    };

    if(id === undefined){
        $.post(URL,data,okFunc,errFunc);
    }else{
        $.put(URL,data,okFunc,errFunc);
    }
}

function addCategory() {
    var div = $("#perCategory");
    var label = $("<label><input type='text'/><span class='close'>X</span></label>");
    div.append(label);
    var inp = label.find("input");
    inp.focus();
    inp.keyup(function (e) {
        if(e.keyCode === 13){
            var val = inp.val();
            if(val === undefined || val.length === 0){
                return;
            }
            $.post("/text/category",{
                categoryName:val
            },function (data) {
                if(div.find("label").length > 9){
                    var p = $(".addCategory");
                    p.html("最多添加10个分类，请前往个人中心编辑!");
                    p.css("color","red");
                }
                div.append("\n<label><input type=\"checkbox\"/>"+val+"</label>");
                label.remove();
            });
        }
    });
}