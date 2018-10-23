/**
 * @author  xiaohe
 * @time    17-11-12 012
 */

var URL = "/text/article";
var DETAIL_URL = "/text/article/detail/";
var editor;
$(function(){
    mouseMoveOn=false;
    mouseClickOn=false;
    var E = window.wangEditor;
    editor = new E(".tool",".editor");
    editor.customConfig.zIndex = 0;
    editor.customConfig.customUploadImg = function (files, insert) {
        var file = files[0];
        var pic = file.name;
        var picType = pic.substring(pic.lastIndexOf("."), pic.length).toUpperCase();
        if (picType !== ".JPG" && picType !== ".PNG" && picType !== ".BMP") {
            alert("请选择jpg、png或bmp格式的图片");
            return;
        }
        var fileSize = (file.size/1024).toFixed(2);
        if(fileSize > 2048){
            alert("最大支持2M的图片");
            return;
        }
        calculate(file,function (md5) {
            var formData = new FormData();
            formData.append("file",file);
            formData.append("md5",md5);
            $.ajax({
                url: "/org/usr/file/article",
                data: formData,
                type: "post",
                cache: false,
                dataType: "json",
                contentType: false,
                processData: false,
                success: function (data) {
                    log(data);
                    insert("/org/usr/file/img/"+data.data);
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

    //xh todo:只在修改了文章内容时弹出
    /*window.onbeforeunload = function(e) {
        var dialogText = '您要离开该页面吗？您所做的修改可能还未保存。';
        e.returnValue = dialogText;
        return dialogText;
    };*/

    $(document).on("click",".close",function () {
        $(this).parent().remove();
    });

    if(articleId !== ""){
        $("#sysCategory").find("input[value='"+sysCategoryId+"']").attr("checked","checked");
        $.each(perCategoryIds.split(","),function (i, data) {
            $("#perCategory").find("input[value='"+data+"']").attr("checked","checked");
        });
    }else{
        $("#sysCategory").find("input:first").attr("checked","checked");
    }

    $("input[name='secretLevel']"+(secretLevel !== "" ? "[value='"+secretLevel+"']":":first")).attr('checked', 'checked');

    $(".addCategory").click(addCategory);

    $(".w-e-text").css({
        "overflow-y":"hidden",
        "height":"auto"
    });

    $("#editorArticle").addClass("active");

    var validator = new Validator();

    validator.add({
        dom:$("#articleTitle"),
        maxLength:16,
        msg:{
            empty:"标题不能为空",
            maxLength:"标题字数过长"
        },
        call:function (dom, msg) {
            dom.next().text(msg);
        }
    });

    var lock = true;
    $("#publish").click(function () {
        if(!validator.verify()){
            alert("验证不通过，请修改后再重新发表");
            return;
        }

        var sysCategory = $("#sysCategory").find("input:checked").val();
        var perCategory = [];
        $.each($("#perCategory").find("input:checked"),function(i,data){
            perCategory.push(parseInt($(data).val()));
        });

        var data = {
            title:$("#articleTitle").val(),
            text:editor.txt.html(),
            perCategoryIds:perCategory,
            sysCategoryId:sysCategory,
            secretLevel:$("input[name='secretLevel']:checked").val(),
            id:articleId
        };

        //发布锁，防止重复发布
        if(!lock)return;
        lock = false;
        $.hint("发表中……");

        var okFunc = function(data){
            $.hint("发表成功……");
            setTimeout(function () {
                location.href = DETAIL_URL +data;
            },1000);
        };

        var errFunc = function (err) {
            $.maskHint("sorry!系统出现未知错误");
            lock = true;
        };

        if(articleId === ""){
            post(URL,data,okFunc,errFunc);
        }else{
            put(URL,data,okFunc,errFunc);
        }
    });

});

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
            post("/text/category",{
                categoryName:val
            },function (data) {
                if(div.find("label").length > 9){
                    var p = $(".addCategory");
                    p.html("最多添加10个分类，请前往个人中心编辑!");
                    p.css("color","red");
                }
                div.append("\n<label><input type=\"checkbox\" value=\""+data.id+"\"/>"+val+"</label>");
                label.remove();
            });
        }
    });
}