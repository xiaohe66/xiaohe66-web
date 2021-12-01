package com.xiaohe66.web.domain.love.agg;

import com.xiaohe66.web.domain.love.value.LoverId;
import com.xiaohe66.web.domain.love.value.NewsId;
import com.xiaohe66.web.domain.love.value.NewsText;
import com.xiaohe66.web.integration.domain.Aggregate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * @author xiaohe
 * @since 2021.12.01 17:11
 */
@Builder
@Getter
@ToString
@AllArgsConstructor
public class News implements Aggregate<News, NewsId> {

    @NonNull
    private final NewsId id;

    @NonNull
    private final LoverId loverId;

    private final NewsText text;

    @Override
    public boolean hasSameRootAttribute(News other) {
        return other != null;
    }
}
