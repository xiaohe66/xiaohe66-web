package com.xiaohe66.web.infrastructure.domain.adapter.love;

import com.xiaohe66.web.domain.love.value.WishDesc;
import com.xiaohe66.web.domain.love.value.WishFinishDate;
import com.xiaohe66.web.domain.love.value.WishFinished;
import com.xiaohe66.web.domain.love.value.WishId;
import com.xiaohe66.web.domain.love.value.WishRemark;
import com.xiaohe66.web.domain.love.value.WishTitle;
import com.xiaohe66.web.infrastructure.domain.adapter.DataConverter;

import java.time.LocalDate;

/**
 * @author xiaohe
 * @since 2022.03.30 11:14
 */
public interface WishConverter extends DataConverter {

    default WishId newField(Long value) {
        return ifPresent(value, WishId::new);
    }

    default WishTitle newTitle(String value) {
        return ifPresent(value,
                WishTitle::new);
    }

    default WishDesc newDesc(String value) {
        return ifPresent(value, WishDesc::new);
    }

    default WishFinished newField(Boolean value) {
        return ifPresent(value, WishFinished::valueOf);
    }

    default WishFinishDate newField(LocalDate value) {
        return ifPresent(value, WishFinishDate::new);
    }

    default WishRemark newRemark(String value) {
        return ifPresent(value, WishRemark::new);
    }
}
