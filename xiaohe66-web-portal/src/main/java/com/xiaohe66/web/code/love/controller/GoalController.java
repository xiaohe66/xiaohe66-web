package com.xiaohe66.web.code.love.controller;

import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.base.base.BaseController;
import com.xiaohe66.web.code.love.dto.GoalDto;
import com.xiaohe66.web.code.love.po.Goal;
import com.xiaohe66.web.code.love.service.GoalService;

/**
 * @author xiaohe
 * @time 2020.01.07 12:13
 */
@XhController("/love/goal")
public class GoalController extends BaseController<GoalService, Goal, GoalDto> {
}
