package com.xiaohe66.web.infrastructure.domain.adapter.love;

import com.xiaohe66.web.domain.love.value.DailyDesc;
import com.xiaohe66.web.domain.love.value.DailyId;
import com.xiaohe66.web.domain.love.value.DailyMood;
import com.xiaohe66.web.infrastructure.domain.adapter.DataConverter;

/**
 * @author xiaohe
 * @since 2022.03.30 11:40
 */
public interface DailyConverter extends DataConverter {

    default DailyId newDailyId(Long id) {
        return ifPresent(id, DailyId::new);
    }

    default DailyDesc newDailyDesc(String desc) {
        return ifPresent(desc, DailyDesc::new);
    }

    default DailyMood newDailyMod(Integer mood) {
        return ifPresent(mood, DailyMood::valueOf);
    }
}
