package com.xiaohe66.web.infrastructure.mybatis.love.convert;

import com.xiaohe66.web.domain.love.agg.Wish;
import com.xiaohe66.web.infrastructure.domain.adapter.love.WishConverter;
import com.xiaohe66.web.infrastructure.mybatis.love.model.WishDo;
import com.xiaohe66.web.integration.DoConverter;
import org.mapstruct.Mapper;

/**
 * @author xiaohe
 * @since 2022-03-28 18:13
 */
@Mapper
public interface WishDoConverter extends DoConverter<Wish, WishDo>, WishConverter {

}
