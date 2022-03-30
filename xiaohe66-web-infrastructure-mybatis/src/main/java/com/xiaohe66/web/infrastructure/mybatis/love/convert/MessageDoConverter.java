package com.xiaohe66.web.infrastructure.mybatis.love.convert;

import com.xiaohe66.web.domain.love.agg.Message;
import com.xiaohe66.web.infrastructure.domain.adapter.love.MessageConverter;
import com.xiaohe66.web.infrastructure.mybatis.love.model.MessageDo;
import com.xiaohe66.web.integration.DoConverter;
import org.mapstruct.Mapper;

/**
 * @author xiaohe
 * @since 2021.12.07 11:48
 */
@Mapper
public interface MessageDoConverter extends DoConverter<Message, MessageDo>, MessageConverter {

}
