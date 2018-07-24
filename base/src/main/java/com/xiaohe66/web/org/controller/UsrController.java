package com.xiaohe66.web.org.controller;

import com.xiaohe66.web.common.annotation.Del;
import com.xiaohe66.web.common.annotation.Get;
import com.xiaohe66.web.common.annotation.Page;
import com.xiaohe66.web.common.annotation.Post;
import com.xiaohe66.web.common.annotation.Put;
import com.xiaohe66.web.common.annotation.XhController;
import com.xiaohe66.web.org.dto.UsrDto;
import com.xiaohe66.web.org.po.Usr;
import com.xiaohe66.web.org.service.UsrService;
import com.xiaohe66.web.security.service.LoginService;
import com.xiaohe66.web.sys.controller.PageController;
import com.xiaohe66.web.sys.dto.CurrentUsr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOG = LoggerFactory.getLogger(UsrController.class);

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
        model.addAttribute("usrDto",usrService.lookAtUsr(currentUsr.getId()));
        return PageController.USR_ZONE_PAGE_URL;
    }

    @Get("/login")
    public Boolean isLogin(){
        return loginService.isLogin();
    }

    @Post("/login")
    public UsrDto login(String usrName, String usrPwd){
        return loginService.login(usrName,usrPwd);
    }

    @Del("/login")
    public  void logout(){
        LOG.info("注销登录");
        loginService.logout();
    }

    @Post
    public UsrDto register(Usr usr, String code){
        return loginService.register(usr,code);
    }

    @Put
    public void update(CurrentUsr currentUsr,String signature){
        Usr usr = new Usr();
        usr.setId(currentUsr.getId());
        usr.setSignature(signature);
        usrService.updateById(usr,currentUsr.getId());
    }

    @Post("/img")
    public Long uploadHeadImg(CurrentUsr currentUsr, @RequestParam("file") MultipartFile file, String md5){
        return usrService.uploadHeadImg(file,md5,currentUsr.getId());
    }

    @Get("/exist/{usrName}")
    public Boolean isExist(@PathVariable("usrName") String usrName){
        return usrService.isExist(usrName);
    }

}
