package com.xiaohe66.web.org.controller;

import com.xiaohe66.web.common.annotation.Del;
import com.xiaohe66.web.common.annotation.Get;
import com.xiaohe66.web.common.annotation.Page;
import com.xiaohe66.web.common.annotation.Post;
import com.xiaohe66.web.common.annotation.Put;
import com.xiaohe66.web.common.annotation.XhController;
import com.xiaohe66.web.org.po.Usr;
import com.xiaohe66.web.org.service.UsrService;
import com.xiaohe66.web.security.service.LoginService;
import com.xiaohe66.web.sys.controller.PageController;
import com.xiaohe66.web.sys.dto.CurrentUsr;
import com.xiaohe66.web.sys.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author xiaohe
 * @time 17-10-28 028
 */
@XhController("/org/usr")
public class UsrController{

    private static final String INDEX_PAGE_URL = "org/login";

    private static final String REGISTER_PAGE_URL = "org/register";

    private static final String USR_DATA_PAGE_URL = "org/usr_data";

    @Autowired
    private LoginService loginService;

    @Autowired
    private UsrService usrService;

    @Page("/index")
    public String index(){
        return INDEX_PAGE_URL;
    }

    @Page("/register")
    public String register(){
        return REGISTER_PAGE_URL;
    }

    @Page("/me")
    public String me(Model model, CurrentUsr currentUsr){
        model.addAttribute("page",USR_DATA_PAGE_URL);
        return PageController.USR_ZONE_PAGE_URL;
    }

    @Get("/login")
    public Result isLogin(){
        return Result.ok(loginService.isLogin());
    }

    @Post("/login")
    public Result login(String usrName, String usrPwd){
        return Result.ok(loginService.login(usrName,usrPwd));
    }

    @Del("/login")
    public  Result logout(){
        loginService.logout();
        return Result.ok(null);
    }

    @Post
    public Result register(Usr usr, String code){
        return Result.ok(loginService.register(usr,code));
    }

    @Put
    public Result update(CurrentUsr currentUsr,String signature){
        Usr usr = new Usr();
        usr.setId(currentUsr.getId());
        usr.setSignature(signature);
        usrService.updateById(usr,currentUsr.getId());
        return Result.ok();
    }

    @Post("/img")
    public Result uploadHeadImg(CurrentUsr currentUsr, @RequestParam("file") MultipartFile file, String md5){
        return Result.ok(usrService.uploadHeadImg(file,md5,currentUsr.getId()));
    }

    @Get("/exist/{usrName}")
    public Result isExist(@PathVariable("usrName") String usrName){
        return Result.ok(usrService.isExist(usrName));
    }

}
