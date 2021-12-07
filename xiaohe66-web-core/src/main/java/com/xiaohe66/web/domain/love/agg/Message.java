package com.xiaohe66.web.domain.love.agg;

import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.love.value.LoverId;
import com.xiaohe66.web.domain.love.value.MessageId;
import com.xiaohe66.web.domain.love.value.MessageText;
import com.xiaohe66.web.integration.domain.Aggregate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * @author xiaohe
 * @since 2021.12.07 11:42
 */
@Builder
@Getter
@ToString
@AllArgsConstructor
public class Message implements Aggregate<Message, MessageId> {

    @NonNull
    private final MessageId id;

    @NonNull
    private final AccountId createId;

    @NonNull
    private final LoverId loverId;

    @NonNull
    private final MessageText text;

    @Override
    public boolean hasSameRootAttribute(Message other) {
        return other != null;
    }
}
