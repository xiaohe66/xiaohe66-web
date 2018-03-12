package com.xiaohe66.web.test.controller;

import com.xiaohe66.web.common.annotation.Get;
import com.xiaohe66.web.common.annotation.Post;
import com.xiaohe66.web.common.annotation.Put;
import com.xiaohe66.web.common.annotation.XhController;
import com.xiaohe66.web.common.data.CodeEnum;
import com.xiaohe66.web.common.exception.XhException;
import com.xiaohe66.web.org.po.Usr;
import com.xiaohe66.web.sys.dto.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xiaohe
 * @time 17-10-30 030
 */
@XhController("/xh")
public class TestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

//    @Page("/page")
    public String page(Usr usr, Model model){
        model.addAttribute("usrName",usr.getUsrName());
        return "index";
    }

    @Get("/test")
    public Result test(Usr usr){
        return Result.ok(usr.getUsrName());
    }

    @Post("/test")
    public Result test1(Usr usr){
        return Result.ok(usr.getUsrName());
    }

    @Put("/test")
    public ResponseEntity<Result> test3(Long id,Usr usr){
        LOGGER.error(id+"");
        LOGGER.error(usr == null? null:usr.getUsrName()+usr.getId());
        throw new XhException(CodeEnum.RESOURCE_NOT_FOUND);
//        return usr.toString();
    }
    public String test4(Usr usr){
        return usr.toString();
    }

    @RequestMapping("/test1")
    @ResponseBody
    public String test(){
        return "ok";
    }

    @RequestMapping("test2")
    @ResponseBody
    public String test2(){
        return "ok";
    }
}
