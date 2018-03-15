/**
 * @author  xiaohe
 * @time    17-11-12 012
 */

var URL = "/text/article";
var DETAIL_URL = "/text/article/detail";
var editor;
$(function(){
    var E = window.wangEditor;
    editor = new E(".tool",".editor");
    editor.customConfig.zIndex = 0;
    editor.create();
    // editor.$textElem.attr('contenteditable', false)
    
    $(document).on("click",".close",function () {
        $(this).parent().remove();
    });
});

function publish() {
    var id = $("#id").val();
    var type = id  === undefined ? "post" : "put";
    var data = {
        title:$(".title").val(),
        text:editor.txt.html(),
        publish:true,
        id:id
    };
    var okFunc = function(data){
        alert("发表成功");

        location.href = DETAIL_URL + "?id="+data;
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
    var div = $("#categoryPerson");
    var inputs = div.find("input");
    if(inputs.length >= 6){
        alert("不能再添加了");
        return;
    }
    var label = $("<label><input type='text'/><span class='close'>X</span></label>");
    div.append(label);
    var inp = label.find("input");
    inp.focus();
    inp.keyup(function (e) {
        if(e.keyCode === 13){
            var val = inp.val();
            if(val === undefined || val.length === 0){
                alert("请添加");
                return;
            }
            div.append("<label><input type='checkbox'/>"+val+"</label>");
            label.remove();
        }
    });
}