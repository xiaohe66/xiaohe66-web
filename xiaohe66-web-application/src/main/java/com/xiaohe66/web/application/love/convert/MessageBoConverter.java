package com.xiaohe66.web.application.love.convert;

import com.xiaohe66.web.application.love.result.MessageListResult;
import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.love.agg.Message;
import com.xiaohe66.web.domain.wx.user.aggregate.WxUser;
import com.xiaohe66.web.domain.wx.user.repository.WxUserRepository;
import com.xiaohe66.web.integration.domain.DataConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiaohe
 * @since 2021.12.07 12:05
 */
@Mapper(componentModel = "spring")
public interface MessageBoConverter extends DataConverter {

    default List<MessageListResult> toResult(WxUserRepository wxUserRepository, List<Message> list, AccountId currentUserId) {

        return list.stream()
                .map(message -> toResult(wxUserRepository, message, currentUserId))
                .collect(Collectors.toList());
    }

    default MessageListResult toResult(WxUserRepository wxUserRepository, Message message, AccountId currentUserId) {

        MessageListResult result = toResult(message);

        result.setIsMe(message.getCreateId().equals(currentUserId));

        WxUser wxUser = wxUserRepository.getByAccountId(message.getCreateId());
        if (wxUser.getNickname() != null) {
            result.setNickname(wxUser.getNickname().getValue());
        }
        if (wxUser.getAvatarUrl() != null) {
            result.setAvatarUrl(wxUser.getAvatarUrl().getValue());
        }

        return result;
    }

    @Mapping(source = "id", target = "createTime")
    MessageListResult toResult(Message message);

}
