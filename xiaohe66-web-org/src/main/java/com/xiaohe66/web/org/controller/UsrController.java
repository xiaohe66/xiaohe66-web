package com.xiaohe66.web.org.controller;

import com.xiaohe66.web.base.annotation.Get;
import com.xiaohe66.web.base.annotation.Put;
import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.org.helper.UsrHelper;
import com.xiaohe66.web.org.po.Usr;
import com.xiaohe66.web.org.service.UsrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author xiaohe
 * @time 17-10-28 028
 */
@XhController("/org/usr")
public class UsrController{

    @Autowired
    private UsrService usrService;

    @Put
    public void update(String signature){
        Long currentUsrId = UsrHelper.getCurrentUsrId();

        Usr usr = new Usr();
        usr.setId(currentUsrId);
        usr.setSignature(signature);
        usrService.updateById(usr,currentUsrId);
    }

    @Get("/exist/{usrName}")
    public Boolean isExist(@PathVariable("usrName") String usrName){
        return usrService.isExist(usrName);
    }

}