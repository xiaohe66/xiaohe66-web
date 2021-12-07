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
import com.xiaohe66.web.domain.wx.user.aggregate.WxUser;
import com.xiaohe66.web.domain.wx.user.repository.WxUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @NeedRoles({RoleName.WX_ROLE_VALUE, RoleName.LOVE_ROLE_VALUE})
    public R<Long> save(MessageSaveBo bo) {

        AccountId currentAccountId = securityService.getCurrentAccountId();

        long id = IdWorker.genId();

        Message message = Message.builder()
                .id(new MessageId(id))
                .createId(currentAccountId)
                .loverId(new LoverId(1L))
                .text(new MessageText(bo.getText()))
                .build();

        messageService.save(message);

        return R.ok(id);
    }

    @NeedRoles(RoleName.LOVE_ROLE_VALUE)
    public R<List<MessageListResult>> list(MessageListBo bo) {

        LoverId loverId = new LoverId(bo.getLoverId());

        List<Message> messageList = messageService.list(loverId, bo.toPaging());

        List<MessageListResult> list = messageList.stream().map(message -> {

            MessageListResult result = boConverter.toResult(message);
            WxUser wxUser = wxUserRepository.getByAccountId(message.getCreateId());
            if (wxUser.getNickname() != null) {
                result.setNickname(wxUser.getNickname().getValue());
            }
            if (wxUser.getAvatarUrl() != null) {
                result.setAvatarUrl(wxUser.getAvatarUrl().getValue());
            }
            return result;
        }).collect(Collectors.toList());

        return R.ok(list);
    }

}
