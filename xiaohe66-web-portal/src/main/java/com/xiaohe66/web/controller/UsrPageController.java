package com.xiaohe66.web.controller;

import com.xiaohe66.web.base.annotation.Page;
import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.org.helper.UsrHelper;
import com.xiaohe66.web.org.service.UsrService;
import org.springframework.ui.Model;

import javax.annotation.Resource;

/**
 * @author xiaohe
 * @time 17-10-28 028
 */
@XhController("/org/usr")
public class UsrPageController {

    private static final String INDEX_PAGE_URL = "org/login";

    private static final String REGISTER_PAGE_URL = "org/register";

    private static final String USR_DATA_PAGE_URL = "org/usr_data";

    @Resource
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
    public String me(Model model){
        model.addAttribute("page",USR_DATA_PAGE_URL);
        model.addAttribute("usrDto",usrService.lookAtUsr(UsrHelper.getCurrentUsrId()));
        return OtherPageController.USR_ZONE_PAGE_URL;
    }

}
