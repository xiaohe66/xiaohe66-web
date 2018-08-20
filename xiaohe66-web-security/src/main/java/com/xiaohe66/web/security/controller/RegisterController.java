package com.xiaohe66.web.security.controller;

import com.xiaohe66.web.base.annotation.Post;
import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.org.dto.UsrDto;
import com.xiaohe66.web.org.po.Usr;
import com.xiaohe66.web.security.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户注册
 *
 * @author xh
 * @version 1.0
 * @time 2018-08-20 12:06
 */
@XhController("/usr/register")
public class RegisterController {

    @Autowired
    private LoginService loginService;

    @Post
    public UsrDto register(Usr usr, String code){
        return loginService.register(usr,code);
    }

}
