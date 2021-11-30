package com.xiaohe66.web.domain.love.agg;

import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.love.value.DailyDesc;
import com.xiaohe66.web.domain.love.value.DailyId;
import com.xiaohe66.web.domain.love.value.DailyMood;
import com.xiaohe66.web.domain.love.value.LoverId;
import com.xiaohe66.web.integration.domain.Aggregate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.Objects;

/**
 * @author xiaohe
 * @since 2021.11.30 12:08
 */
@Builder
@Getter
@ToString
@AllArgsConstructor
public class Daily implements Aggregate<Daily, DailyId> {

    @NonNull
    private final DailyId id;

    @NonNull
    private final AccountId createId;

    @NonNull
    private final LoverId loverId;

    private DailyDesc desc;
    private DailyMood mood;

    @Override
    public boolean hasSameRootAttribute(Daily other) {
        return other != null &&
                Objects.equals(desc, other.desc) &&
                Objects.equals(mood, other.mood);
    }
}
