/**
 * @author  xiaohe
 * @time    17-11-12 012
 */
function logout(){
    del("/sec/login",function(data){
        location.reload();
    });
}