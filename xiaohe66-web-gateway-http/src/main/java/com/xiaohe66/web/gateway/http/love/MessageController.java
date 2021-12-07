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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xiaohe
 * @since 2021.12.07 15:45
 */
@RestController
@RequestMapping("/love/message")
@RequiredArgsConstructor
public class MessageController {

    private final MessageDtoConverter dtoConverter;
    private final MessageAppService messageAppService;

    @PostMapping
    public R<MessageListResult> save(@Validated @RequestBody MessageSaveDto dto) {
        MessageSaveBo bo = dtoConverter.toBo(dto);
        return messageAppService.save(bo);
    }

    @DeleteMapping("/{id}")
    public R<Void> delete(@PathVariable Long id){
        return messageAppService.remove(id);
    }

    @GetMapping
    public R<List<MessageListResult>> list(@Validated MessageListDto dto) {
        MessageListBo bo = dtoConverter.toBo(dto);
        return messageAppService.list(bo);
    }

}
