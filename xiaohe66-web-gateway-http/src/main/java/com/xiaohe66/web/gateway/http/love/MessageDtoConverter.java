package com.xiaohe66.web.gateway.http.love;

import com.xiaohe66.web.application.love.bo.MessageListBo;
import com.xiaohe66.web.application.love.bo.MessageSaveBo;
import com.xiaohe66.web.gateway.http.DtoConverter;
import com.xiaohe66.web.gateway.http.love.dto.MessageListDto;
import com.xiaohe66.web.gateway.http.love.dto.MessageSaveDto;
import org.mapstruct.Mapper;

/**
 * @author xiaohe
 * @since 2021.12.07 15:48
 */
@Mapper(componentModel = "spring")
public interface MessageDtoConverter extends DtoConverter {

    MessageListBo toBo(MessageListDto dto);

    MessageSaveBo toBo(MessageSaveDto dto);

}
