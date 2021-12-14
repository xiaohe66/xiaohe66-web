package com.xiaohe66.web.domain.love.service;

import com.xiaohe66.web.domain.love.agg.Lover;
import com.xiaohe66.web.domain.love.agg.Message;
import com.xiaohe66.web.domain.love.repository.LoverRepository;
import com.xiaohe66.web.domain.love.repository.MessageRepository;
import com.xiaohe66.web.domain.love.value.LoverId;
import com.xiaohe66.web.domain.love.value.MessageId;
import com.xiaohe66.web.domain.sys.sec.service.SecurityService;
import com.xiaohe66.web.integration.domain.Paging;
import com.xiaohe66.web.integration.ex.ErrorCodeEnum;
import com.xiaohe66.web.integration.util.Assert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiaohe
 * @since 2021.12.07 11:50
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final LoverRepository loverRepository;
    private final SecurityService securityService;


    public void save(Message message) {

        messageRepository.save(message);
    }

    public void remove(MessageId id) {

        Message message = messageRepository.getById(id);

        securityService.checkCreatorPermission(message.getCreateId());

        messageRepository.removeById(id);
    }

    public List<Message> listDesc(LoverId loverId, Paging paging) {

        return messageRepository.listDesc(loverId, paging);
    }

}
