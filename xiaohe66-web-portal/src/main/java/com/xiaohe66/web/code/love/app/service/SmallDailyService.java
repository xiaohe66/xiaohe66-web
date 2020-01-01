package com.xiaohe66.web.code.love.app.service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.code.love.app.mapper.SmallDailyMapper;
import com.xiaohe66.web.code.love.app.po.SmallDaily;
import com.xiaohe66.web.code.org.helper.UserHelper;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @author xiaohe
 * @time 2019.12.06 16:13
 */
@Service
public class SmallDailyService extends AbstractService<SmallDailyMapper, SmallDaily> {

    @Override
    public boolean removeById(Serializable id) {
        // todo : 考虑抽象到 AbstractService 中
        Integer currentUsrId = UserHelper.getCurrentUsrId();

        SmallDaily smallDaily = new SmallDaily();
        smallDaily.setId((Integer) id);
        smallDaily.setCreateId(currentUsrId);

        UpdateWrapper<SmallDaily> updateWrapper = new UpdateWrapper<>(smallDaily);

        return remove(updateWrapper);
    }
}