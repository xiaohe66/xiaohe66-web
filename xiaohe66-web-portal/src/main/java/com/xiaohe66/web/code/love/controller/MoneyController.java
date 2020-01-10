package com.xiaohe66.web.code.love.controller;

import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.base.base.BaseController;
import com.xiaohe66.web.code.love.dto.MoneyDto;
import com.xiaohe66.web.code.love.po.Money;
import com.xiaohe66.web.code.love.service.MoneyService;

/**
 * @author xiaohe
 * @time 2020.01.10 12:06
 */
@XhController("/love/money")
public class MoneyController extends BaseController<MoneyService, Money, MoneyDto> {
}
