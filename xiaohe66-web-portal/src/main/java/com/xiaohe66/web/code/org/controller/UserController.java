package com.xiaohe66.web.code.org.controller;

import com.xiaohe66.web.base.annotation.Get;
import com.xiaohe66.web.base.annotation.Post;
import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.base.base.BaseController;
import com.xiaohe66.web.code.org.dto.UserDto;
import com.xiaohe66.web.code.org.po.User;
import com.xiaohe66.web.code.org.service.UserService;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author xiaohe
 * @time 17-10-28 028
 */
@XhController("/org/usr")
public class UserController extends BaseController<UserService, User, UserDto> {

    @Get("name/{usrName}")
    public Boolean usrNameIsExist(@PathVariable String usrName) {
        return baseService.isExistUserName(usrName);
    }

    @Post("email/email")
    public Boolean emailIsExist(String email) {
        return baseService.isExistEmail(email);
    }

}
