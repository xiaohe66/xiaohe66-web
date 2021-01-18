package com.xiaohe66.web.code.love.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xiaohe66.web.code.love.mapper.DailyMapper;
import com.xiaohe66.web.code.love.po.Daily;
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
public class DailyService extends LoveService<DailyMapper, Daily> {

    @Override
    public boolean removeById(Serializable id) {

        Integer currentUserLoverId = loverService.getCurrentUserLoverId();

        Daily smallDaily = new Daily();
        smallDaily.setId((Integer) id);
        // todo : 超级管理员可删除全部
        smallDaily.setLoverId(currentUserLoverId);

        UpdateWrapper<Daily> updateWrapper = new UpdateWrapper<>(smallDaily);

        return remove(updateWrapper);
    }


    @Override
    public QueryWrapper<Daily> createDefaultQueryWrapper(Daily po) {
        QueryWrapper<Daily> queryWrapper = super.createDefaultQueryWrapper(po);
        queryWrapper.orderByDesc("create_time");
        return queryWrapper;
    }

}