package com.xiaohe66.web.gateway.http.love;

import com.xiaohe66.common.dto.R;
import com.xiaohe66.web.application.love.MessageAppService;
import com.xiaohe66.web.application.love.bo.MessageListBo;
import com.xiaohe66.web.application.love.bo.MessageSaveBo;
import com.xiaohe66.web.application.love.result.MessageListResult;
import com.xiaohe66.web.gateway.http.love.dto.MessageListDto;
import com.xiaohe66.web.gateway.http.love.dto.MessageSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xiaohe
 * @since 2021.12.07 15:45
 */
@RestController("/love/message")
@RequestMapping
@RequiredArgsConstructor
public class MessageController {

    private final MessageDtoConverter dtoConverter;
    private final MessageAppService messageAppService;

    @PostMapping
    public R<Long> save(@Validated @RequestBody MessageSaveDto dto) {
        MessageSaveBo bo = dtoConverter.toBo(dto);
        return messageAppService.save(bo);
    }

    @GetMapping
    public R<List<MessageListResult>> list(@Validated @RequestBody MessageListDto dto) {
        MessageListBo bo = dtoConverter.toBo(dto);
        return messageAppService.list(bo);
    }

}
