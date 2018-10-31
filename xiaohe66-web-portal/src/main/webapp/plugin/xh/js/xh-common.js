/**
 * @author  xiaohe
 * @version 2.0
 * @time    18-05-15
 */
function log(msg) {
    console.log(msg);
}
function isArray(obj) {
    return obj instanceof Array;
}
function isEmpty(obj) {
    return obj===undefined||obj.length===0;
}
function isNotEmpty(obj) {
    return obj!==undefined&&obj.length!==0;
}
function isNumber(num) {
    return num !== undefined && typeof num === "number";
}
function isFunc(func) {
    return func!==undefined && typeof func === "function";
}
function html2Escape(sHtml) {
    return sHtml.replace(/[<>&"]/g,function(c){
        return {'<':'&lt;','>':'&gt;','&':'&amp;','"':'&quot;'}[c];
    });
}
function trim(str) {
    return str?str.replace(/^\s\s*/,'').replace(/\s\s*$/,''):"";
}
function ajax(param,success,error) {
    param.success=function (data) {
        if(data.code === 200){
            if(typeof success === "function")success(data.data);
        }else if(data.code === 603){
            log("没有登录");
            alert("没有登录");
        }else{
            log(data);
            if(error !== undefined)error(data);
        }
    };
    $.ajax(param);
}
function http(url, type, data, success, error, headers) {
    ajax({url:url,data:data,type:type,headers:headers,dataType:"json"},success,error);
}
function get(url,success,error) {
    http(url,"get",{},success,error,{});
}
function post(url,data,success,error) {
    http(url,"post",data,success,error,{});
}
function put(url,data,success,error) {
    http(url,"put",data,success,error,{});
}
function del(url,success,error) {
    http(url,"delete",{},success,error,{});
}
function paging(url,success,num,size,error) {
    http(url,"get",{},success,error,{
        pageSize:size,
        pageNum:num
    });
}