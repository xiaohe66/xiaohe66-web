package com.xiaohe66.web.infrastructure.domain.adapter.file;

import com.xiaohe66.web.domain.file.value.ImageId;
import com.xiaohe66.web.domain.file.value.ImageName;
import com.xiaohe66.web.infrastructure.domain.adapter.DataConverter;

/**
 * @author xiaohe
 * @since 2022.03.30 11:43
 */
public interface ImageConverter extends DataConverter {

    default ImageId newId(Long id) {
        return ifPresent(id, ImageId::new);
    }

    default ImageName newImageName(String name) {
        return ifPresent(name, ImageName::new);
    }
}
