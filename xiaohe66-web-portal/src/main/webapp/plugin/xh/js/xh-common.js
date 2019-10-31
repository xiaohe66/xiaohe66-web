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
    return obj === undefined || obj.length === 0;
}

function isNotEmpty(obj) {
    return obj !== undefined && obj.length !== 0;
}

function isNumber(num) {
    return num !== undefined && typeof num === "number";
}

function isFunc(func) {
    return func !== undefined && typeof func === "function";
}

function html2Escape(sHtml) {
    return sHtml.replace(/[<>&"]/g, function (c) {
        return {'<': '&lt;', '>': '&gt;', '&': '&amp;', '"': '&quot;'}[c];
    });
}

function trim(str) {
    return str ? str.replace(/^\s\s*/, '').replace(/\s\s*$/, '') : "";
}

function ajax(param, success, error) {
    param.success = function (data) {
        if (data.code === 100) {
            if (typeof success === "function") success(data.data);
        } else if (data.code === 421) {
            log("没有登录");
            alert("没有登录");
        } else {
            log(data);
            if (error !== undefined) error(data);
            else alert("系统繁忙");
        }
    };
    $.ajax(param);
}

function http(url, type, data, success, error, headers) {
    ajax({url: url, data: data, type: type, headers: headers, dataType: "json"}, success, error);
}

function get(url, success, error) {
    http(url, "get", {}, success, error, {});
}

function post(url, data, success, error) {
    http(url, "post", data, success, error, {});
}

function put(url, data, success, error) {
    http(url, "put", data, success, error, {});
}

function del(url, success, error) {
    if (!success) success = function(data){alert("删除成功");};
    http(url, "delete", {}, success, error, {});
}

function paging(url, pageNo, pageSize, success, error) {
    http(url, "get", {}, success, error, {
        pageSize: pageSize,
        pageNo: pageNo
    });
}

function getQueryParam(key) {
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i = 0; i < vars.length; i++) {
        var pair = vars[i].split("=");
        if (pair[0] == key) {
            return pair[1];
        }
    }
    return (false);
}

function commonFileImgUrl(id) {
    return "/comm/file/img/"+id;
}
function loveImgUrl(id) {
    return "/love/photo/img/"+id;
}