package com.xiaohe66.web.code.love.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.code.love.mapper.LoverLinkMapper;
import com.xiaohe66.web.code.love.po.LoverLink;
import org.springframework.stereotype.Service;

/**
 * @author xiaohe
 * @time 2020.01.06 16:49
 */
@Service
public class LoverLinkService extends AbstractService<LoverLinkMapper, LoverLink> {

    public LoverLink getLoverLink(Integer loverId, Integer userId) {

        QueryWrapper<LoverLink> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne("user_id", userId);
        queryWrapper.eq("lover_id", loverId);

        return getOne(queryWrapper);
    }
}
