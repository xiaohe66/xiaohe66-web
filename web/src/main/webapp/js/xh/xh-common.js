/**
 * @author  xiaohe
 * @time    17-11-02 002
 */
(function($){
    $.extend({
        log : function(msg){
            try{
                console.log(msg);
            }catch(e){
                // alert(msg);
            }
        },
        http : function(type,url,data,success,error,headers){
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
        get : function(url,data,success,error){
            return $.http("get",url,data,success,error);
        },
        post : function(url,data,success,error){
            return $.http("post",url,data,success,error);
        },
        put : function(url,data,success,error){
            return $.http("put",url,data,success,error);
        },
        del : function(url,data,success,error){
            return $.http("delete",url,data,success,error);
        }
    });
})(jQuery);