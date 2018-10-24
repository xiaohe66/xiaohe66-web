/**
 * 表单验证
 *
 * param 参数说明：
 *  dom              jquery对象，需要进行表单验证的obj
 *  min              数字类型最小值(包含)，默认不验证
 *  max              数字类型最大值(包含)，默认不验证
 *  minLength        字符类型最小长度(包含)，默认不验证
 *  maxLength        字符类型最大长度(包含)，默认不验证
 *  empty            验证字符串是否为空（去掉前后空格进行验证）
 *                   设置为true时表示需要验证，为false时表示不需要验证，默认为true
 *
 *  func(dom,msg)    自定义验证函数，在其它验证类型全部验证通过后调用
 *                   该函数返回true时表示验证通过，返回false或字符串表示不通过，返回的字符串会作为msg参数传递给其它回调函数
 *
 *  ok(dom,msg)      回调函数，验证成功后回调
 *  err(dom,msg)     回调函数，验证失败后回调
 *  call(dom,msg)    回调函数，无论成功与否，都会回调，在ok和err之后调用
 *
 * @author xh
 * @time 18-10-17 017
 */
function Validator(){

    var params = [];
    var message = {
        ok:"",
        empty:"不能为空",
        regex:"格式错误",
        maxLength:"字数过长",
        check:"验证不通过"
    };

    var verifyItem = function (param) {
        var dom = param.dom;
        var val = trim(dom.val());
        var msg = param.msg === undefined ? {}:param.msg;
        var call = function (msg) {
            if(isFunc(param.call)){
                param.call(dom,msg);
            }
        };
        var err = function (msg) {
            if(isFunc(param.err)){
                param.err(dom,msg);
            }
            call(msg);
        };
        var isVerifyEmpty = isEmpty(param.empty) || param.empty;
        if (isVerifyEmpty && isEmpty(val)) {
            var empty = msg.empty;
            err(isEmpty(empty)?message.empty:empty);
            return;
        }
        if (isNumber(param.maxLength) && val.length > param.maxLength) {
            var maxLength = msg.maxLength;
            err(isEmpty(maxLength)?message.maxLength:maxLength);
            return;
        }
        if(isNotEmpty(param.regex) && !param.regex.test(val)){
            var regex = msg.regex;
            err(isEmpty(regex)?message.regex:regex);
            return;
        }
        if(isFunc(param.check)){
            var ret = param.check(dom,val);
            if(typeof ret === "string"){
                err(ret);
                return;
            }else if(!ret){
                var check = msg.check;
                err(isEmpty(check)?message.check:check);
                return;
            }
        }
        if(isFunc(param.ok)){
            param.ok(dom,message.ok);
        }
        call("");
        return true;
    };

    this.setMessage = function (messages) {
        for(var field in messages){
            message[field] = messages[field];
        }
    };

    this.add = function (param) {
        params.push(param);
        param.dom.blur(function (){
            verifyItem(param);
        });
    };

    this.verify = function () {
        var pass = true;
        $.each(params,function (i, param) {
            if(!verifyItem(param)){
                pass = false;
            }
        });
        return pass;
    }

}
