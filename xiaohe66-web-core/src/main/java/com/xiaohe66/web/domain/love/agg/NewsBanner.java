package com.xiaohe66.web.domain.love.agg;

import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.file.value.ImageId;
import com.xiaohe66.web.domain.love.value.LoverId;
import com.xiaohe66.web.domain.love.value.NewsBannerId;
import com.xiaohe66.web.domain.love.value.NewsBannerSort;
import com.xiaohe66.web.integration.domain.Aggregate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

/**
 * @author xiaohe
 * @since 2021.12.19 12:20
 */
@Builder
@Getter
@ToString
@AllArgsConstructor
public class NewsBanner implements Aggregate<NewsBanner, NewsBannerId> {

    private final NewsBannerId id;

    private final AccountId createId;

    private final LoverId loverId;

    private final ImageId imageId;

    private NewsBannerSort sort;

    public void move(NewsBannerSort sort) {
        this.sort = sort;
    }

    @Override
    public boolean hasSameRootAttribute(NewsBanner other) {
        return other != null &&
                Objects.equals(sort, other.sort);
    }
}
