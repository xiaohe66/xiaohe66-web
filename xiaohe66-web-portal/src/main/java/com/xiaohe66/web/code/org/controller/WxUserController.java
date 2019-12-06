package com.xiaohe66.web.code.org.controller;

import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.base.base.BaseController;
import com.xiaohe66.web.code.org.dto.WxUserDto;
import com.xiaohe66.web.code.org.po.WxUser;
import com.xiaohe66.web.code.org.service.WxUserService;

/**
 * @author xiaohe
 * @time 2019.12.06 16:23
 */
@XhController("/org/wxUser")
public class WxUserController extends BaseController<WxUserService, WxUser, WxUserDto> {


}
