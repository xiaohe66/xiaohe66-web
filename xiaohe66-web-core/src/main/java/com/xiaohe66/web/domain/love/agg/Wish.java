package com.xiaohe66.web.domain.love.agg;

import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.love.value.LoverId;
import com.xiaohe66.web.domain.love.value.WishDesc;
import com.xiaohe66.web.domain.love.value.WishFinishDate;
import com.xiaohe66.web.domain.love.value.WishFinished;
import com.xiaohe66.web.domain.love.value.WishId;
import com.xiaohe66.web.domain.love.value.WishRemark;
import com.xiaohe66.web.domain.love.value.WishTitle;
import com.xiaohe66.web.integration.domain.Aggregate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.Objects;

/**
 * @author xiaohe
 * @since 2022-03-28 18:13
 */
@Builder
@Getter
@ToString
@AllArgsConstructor
public class Wish implements Aggregate<Wish, WishId> {

    @NonNull
    private final WishId id;

    @NonNull
    private final AccountId createId;

    @NonNull
    private final LoverId loverId;

    @NonNull
    private WishTitle title;

    @NonNull
    private WishDesc desc;

    @NonNull
    @Builder.Default
    private WishFinished finished = WishFinished.FALSE;

    /**
     * 愿望的完成日期，可以任意指定
     */
    private WishFinishDate finishDate;

    @NonNull
    @Builder.Default
    private WishRemark remark = WishRemark.EMPTY;

    @Override
    public boolean hasSameRootAttribute(Wish other) {
        return other != null &&
                Objects.equals(title, other.title) &&
                Objects.equals(desc, other.desc) &&
                Objects.equals(finished, other.finished) &&
                Objects.equals(finishDate, other.finishDate) &&
                Objects.equals(remark, other.remark);
    }
}
