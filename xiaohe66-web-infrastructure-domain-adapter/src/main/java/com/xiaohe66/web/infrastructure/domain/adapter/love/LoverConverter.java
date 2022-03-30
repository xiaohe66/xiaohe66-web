package com.xiaohe66.web.infrastructure.domain.adapter.love;

import com.xiaohe66.web.domain.love.value.LoverStatus;
import com.xiaohe66.web.infrastructure.domain.adapter.DataConverter;

/**
 * @author xiaohe
 * @since 2022.03.30 11:39
 */
public interface LoverConverter extends DataConverter {

    default LoverStatus newStatus(Integer status) {
        return ifPresent(status, LoverStatus::valueOf);
    }
}
