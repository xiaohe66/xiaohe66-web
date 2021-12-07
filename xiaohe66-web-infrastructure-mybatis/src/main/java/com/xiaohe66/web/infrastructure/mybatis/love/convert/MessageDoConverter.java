package com.xiaohe66.web.infrastructure.mybatis.love.convert;

import com.xiaohe66.web.domain.love.agg.Message;
import com.xiaohe66.web.domain.love.value.MessageId;
import com.xiaohe66.web.domain.love.value.MessageText;
import com.xiaohe66.web.infrastructure.mybatis.love.model.MessageDo;
import com.xiaohe66.web.integration.DoConverter;
import org.mapstruct.Mapper;

/**
 * @author xiaohe
 * @since 2021.12.07 11:48
 */
@Mapper(componentModel = "spring")
public interface MessageDoConverter extends DoConverter<Message, MessageDo> {

    default MessageId newId(Long id) {
        return ifPresent(id, MessageId::new);
    }

    default MessageText newText(String str) {
        return ifPresent(str, MessageText::new);
    }

}
