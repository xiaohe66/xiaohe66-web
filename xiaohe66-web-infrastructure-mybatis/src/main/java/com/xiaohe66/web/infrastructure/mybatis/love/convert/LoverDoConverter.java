package com.xiaohe66.web.infrastructure.mybatis.love.convert;

import com.xiaohe66.web.domain.love.agg.Lover;
import com.xiaohe66.web.domain.love.value.LoverStatus;
import com.xiaohe66.web.infrastructure.mybatis.love.model.LoverDo;
import com.xiaohe66.web.integration.DoConverter;
import org.mapstruct.Mapper;

/**
 * @author xiaohe
 * @since 2021.11.29 12:04
 */
@Mapper(componentModel = "spring")
public interface LoverDoConverter extends DoConverter<Lover, LoverDo> {

    default LoverStatus newStatus(Integer status) {
        return ifPresent(status, LoverStatus::valueOf);
    }

}
