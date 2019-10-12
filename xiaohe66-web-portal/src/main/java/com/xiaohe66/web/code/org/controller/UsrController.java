package com.xiaohe66.web.code.org.controller;

import com.xiaohe66.web.base.annotation.Get;
import com.xiaohe66.web.base.annotation.Post;
import com.xiaohe66.web.base.annotation.Put;
import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.code.org.helper.UsrHelper;
import com.xiaohe66.web.code.org.po.User;
import com.xiaohe66.web.code.org.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author xiaohe
 * @time 17-10-28 028
 */
@XhController("/org/usr")
public class UsrController{

    @Autowired
    private UserService userService;

    @Put
    public void update(String signature){
        Integer currentUsrId = UsrHelper.getCurrentUsrId();

        User user = new User();
        user.setId(currentUsrId);
        user.setSignature(signature);
        user.setUpdateId(currentUsrId);
        userService.updateById(user);
    }

    @Get("/usrName/{usrName}")
    public Boolean usrNameIsExist(@PathVariable("usrName") String usrName){
        return userService.usrNameIsExist(usrName);
    }

    @Post("/email")
    public Boolean emailIsExist(String email){
        return userService.emailIsExist(email);
    }

}
