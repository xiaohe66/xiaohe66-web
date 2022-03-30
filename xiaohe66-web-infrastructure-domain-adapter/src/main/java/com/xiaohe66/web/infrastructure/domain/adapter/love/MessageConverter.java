package com.xiaohe66.web.infrastructure.domain.adapter.love;

import com.xiaohe66.web.domain.love.value.MessageId;
import com.xiaohe66.web.domain.love.value.MessageText;
import com.xiaohe66.web.infrastructure.domain.adapter.DataConverter;

/**
 * @author xiaohe
 * @since 2022.03.30 11:37
 */
public interface MessageConverter extends DataConverter {

    default MessageId newId(Long id) {
        return ifPresent(id, MessageId::new);
    }

    default MessageText newText(String str) {
        return ifPresent(str, MessageText::new);
    }
}
