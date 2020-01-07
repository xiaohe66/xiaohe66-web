package com.xiaohe66.web.code.love.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.code.love.mapper.SmallDailyMapper;
import com.xiaohe66.web.code.love.po.SmallDaily;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @author xiaohe
 * @time 2019.12.06 16:13
 */
@Service
@AllArgsConstructor
@Slf4j
public class SmallDailyService extends AbstractService<SmallDailyMapper, SmallDaily> {

    private LoverService loverService;

    @Override
    public boolean save(SmallDaily po) {
        po.setLoverId(loverService.getCurrentUserLoverId());
        return super.save(po);
    }

    @Override
    public boolean removeById(Serializable id) {

        Integer currentUserLoverId = loverService.getCurrentUserLoverId();

        SmallDaily smallDaily = new SmallDaily();
        smallDaily.setId((Integer) id);
        smallDaily.setLoverId(currentUserLoverId);

        UpdateWrapper<SmallDaily> updateWrapper = new UpdateWrapper<>(smallDaily);

        return remove(updateWrapper);
    }

    @Override
    public QueryWrapper<SmallDaily> createPageDefaultQueryWrapper(SmallDaily po) {

        Integer currentUserLoverId = loverService.getCurrentUserLoverId();

        log.debug("添加默认查询条件, currentUserLoverId : {}", currentUserLoverId);
        po.setLoverId(currentUserLoverId);

        return new QueryWrapper<>(po);
    }
}