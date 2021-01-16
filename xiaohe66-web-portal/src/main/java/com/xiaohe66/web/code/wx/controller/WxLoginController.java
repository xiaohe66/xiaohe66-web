package com.xiaohe66.web.code.wx.controller;

import com.xiaohe66.web.base.annotation.Get;
import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.base.data.Result;
import com.xiaohe66.web.code.wx.service.WxLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author xiaohe
 * @time 2019.12.10 15:15
 */
@XhController("/wx/login")
@Slf4j
public class WxLoginController {

    private WxLoginService service;

    public WxLoginController(WxLoginService service) {
        this.service = service;
    }

    @Get
    public Result login(@RequestParam String code) {

        return service.login(code);
    }

}
