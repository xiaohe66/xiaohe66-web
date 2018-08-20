/**
 * @author  xiaohe
 * @time    17-11-12 012
 */

var URL = "/text/article";
var DETAIL_URL = "/text/article/detail";
var editor;
$(function(){
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
        post(URL,data,okFunc,errFunc);
    }else{
        put(URL,data,okFunc,errFunc);
    }
}