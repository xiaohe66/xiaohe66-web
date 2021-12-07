package com.xiaohe66.web.application.love.convert;

import com.xiaohe66.web.application.love.result.MessageListResult;
import com.xiaohe66.web.domain.love.agg.Message;
import com.xiaohe66.web.integration.domain.DataConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author xiaohe
 * @since 2021.12.07 12:05
 */
@Mapper(componentModel = "spring")
public interface MessageBoConverter extends DataConverter {

    @Mapping(source = "id", target = "createTime")
    MessageListResult toResult(Message message);

    List<MessageListResult> toResult(List<Message> list);

}
