package com.xiaohe66.web.code.wx.controller;

import com.xiaohe66.web.base.annotation.Post;
import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.base.data.Result;
import com.xiaohe66.web.code.org.po.WxUser;
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

    @Post
    public Result login(WxUser wxUser, @RequestParam String code) {

        // 保存这些参数不会因前端的恶意提交而被错误更新
        wxUser.setUserId(null);
        wxUser.setUnionId(null);
        wxUser.setSessionKey(null);
        wxUser.setOpenId(null);

        return service.login(wxUser, code);
    }

}
