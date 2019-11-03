package com.xiaohe66.web.code.love.controller;

import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.base.base.BaseController;
import com.xiaohe66.web.code.love.dto.WishDto;
import com.xiaohe66.web.code.love.po.Wish;
import com.xiaohe66.web.code.love.service.WishService;

/**
 * @author xiaohe
 * @time 2019.10.22 11:15
 */
@XhController("/love/wish")
public class WishController extends BaseController<WishService, Wish, WishDto> {

    @Override
    protected void checkSelect(Wish wish) {
        // 查询不需要检查权限
    }

}
