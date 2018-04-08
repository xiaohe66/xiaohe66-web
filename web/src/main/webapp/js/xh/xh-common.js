/**
 * @author  xiaohe
 * @time    17-11-02 002
 */
(function($){
    var un = undefined;
    $.extend({
        log : function(msg){
            try{
                console.log(msg);
            }catch(e){
                // alert(msg);
            }
        },
        isArray:function (obj) {
            return obj instanceof Array;
        },
        isEmpty:function (obj) {
            return obj===undefined||obj.length===0;
        },
        http : function(type,url,data,headers,success,error){
            $.ajax({
                url:url,
                data:data,
                type:type,
                error:error,
                headers:headers,
                success:function(data){
                    if(data.code === 200){
                        success(data.data);
                    }else{
                        $.log(data);
                        if(error !== undefined)error(data);
                    }
                }
            });
        },
        getPaging : function (url,num,size,data,success,error) {
            $.http("get",url,data,{
                pageSize:size,
                pageNum:num
            },success,error);
        },
        get : function(url,data,success,error){
            return $.http("get",url,data,{},success,error);
        },
        post : function(url,data,success,error){
            return $.http("post",url,data,{},success,error);
        },
        put : function(url,data,success,error){
            return $.http("put",url,data,{},success,error);
        },
        del : function(url,success,error){
            return $.http("delete",url,{},{},success,error);
        }
    });
})(jQuery);