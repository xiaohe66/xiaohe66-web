package com.xiaohe66.web.application.love;

import com.xiaohe66.common.dto.R;
import com.xiaohe66.common.util.IdWorker;
import com.xiaohe66.web.application.aop.annotation.NeedRoles;
import com.xiaohe66.web.application.love.bo.MessageListBo;
import com.xiaohe66.web.application.love.bo.MessageSaveBo;
import com.xiaohe66.web.application.love.convert.MessageBoConverter;
import com.xiaohe66.web.application.love.result.MessageListResult;
import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.love.agg.Message;
import com.xiaohe66.web.domain.love.repository.MessageRepository;
import com.xiaohe66.web.domain.love.service.MessageService;
import com.xiaohe66.web.domain.love.value.LoverId;
import com.xiaohe66.web.domain.love.value.MessageId;
import com.xiaohe66.web.domain.love.value.MessageText;
import com.xiaohe66.web.domain.sys.sec.service.SecurityService;
import com.xiaohe66.web.domain.sys.sec.value.RoleName;
import com.xiaohe66.web.domain.wx.user.repository.WxUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiaohe
 * @since 2021.12.07 11:53
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MessageAppService {

    private final MessageBoConverter boConverter;
    private final MessageRepository messageRepository;
    private final MessageService messageService;

    private final WxUserRepository wxUserRepository;
    private final SecurityService securityService;

    @NeedRoles(RoleName.LOVE_ROLE_VALUE)
    public R<MessageListResult> save(MessageSaveBo bo) {

        AccountId currentAccountId = securityService.getCurrentAccountId();

        long id = IdWorker.genId();

        Message message = Message.builder()
                .id(new MessageId(id))
                .createId(currentAccountId)
                .loverId(new LoverId(bo.getLoverId()))
                .text(new MessageText(bo.getText()))
                .build();

        messageService.save(message);

        MessageListResult result = boConverter.toResult(wxUserRepository, message, currentAccountId);

        return R.ok(result);
    }

    @NeedRoles(RoleName.LOVE_ROLE_VALUE)
    public R<Void> remove(Long idValue){

        MessageId id = new MessageId(idValue);
        messageService.remove(id);
        return R.ok();
    }

    @NeedRoles(RoleName.LOVE_ROLE_VALUE)
    public R<List<MessageListResult>> list(MessageListBo bo) {

        AccountId currentAccountId = securityService.getCurrentAccountId();

        LoverId loverId = new LoverId(bo.getLoverId());

        List<Message> messageList = messageService.listDesc(loverId, bo.toPaging());

        List<MessageListResult> list = boConverter.toResult(wxUserRepository, messageList, currentAccountId);

        return R.ok(list);
    }

}
