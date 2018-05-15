/**
 * @author  xiaohe
 * @time    17-11-12 012
 */
function logout(){
    del("/org/usr/login",function(data){
        location.reload();
    });
}