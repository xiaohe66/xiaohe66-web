package com.xiaohe66.web.infrastructure.domain.adapter.love;

import com.xiaohe66.web.domain.file.value.ImageId;
import com.xiaohe66.web.domain.love.value.NewsBannerId;
import com.xiaohe66.web.domain.love.value.NewsBannerSort;
import com.xiaohe66.web.infrastructure.domain.adapter.DataConverter;

/**
 * @author xiaohe
 * @since 2022.03.30 11:55
 */
public interface NewsBannerConverter extends DataConverter {

    default ImageId newImageId(Long id) {
        return ifPresent(id, ImageId::new);
    }

    default NewsBannerId newsId(Long id) {
        return ifPresent(id, NewsBannerId::new);
    }

    default NewsBannerSort newsId(Integer sort) {
        return ifPresent(sort, NewsBannerSort::valueOf);
    }
}
