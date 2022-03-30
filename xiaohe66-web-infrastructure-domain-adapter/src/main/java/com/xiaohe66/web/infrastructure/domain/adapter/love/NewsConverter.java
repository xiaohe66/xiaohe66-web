package com.xiaohe66.web.infrastructure.domain.adapter.love;

import com.xiaohe66.web.domain.file.value.ImageId;
import com.xiaohe66.web.domain.love.value.NewsId;
import com.xiaohe66.web.domain.love.value.NewsText;
import com.xiaohe66.web.infrastructure.domain.adapter.DataConverter;

/**
 * @author xiaohe
 * @since 2022.03.30 11:37
 */
public interface NewsConverter extends DataConverter {

    default NewsId newId(Long id) {
        return ifPresent(id, NewsId::new);
    }

    default NewsText newText(String text) {
        return ifPresent(text, NewsText::new);
    }

    default ImageId newImageId(Long imageId) {
        return ifPresent(imageId, ImageId::new);
    }
}
