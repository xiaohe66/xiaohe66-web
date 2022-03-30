package com.xiaohe66.web.infrastructure.domain.adapter.file;

import com.xiaohe66.web.domain.file.value.PictureCategory;
import com.xiaohe66.web.domain.file.value.PictureId;
import com.xiaohe66.web.domain.file.value.PicturePath;
import com.xiaohe66.web.infrastructure.domain.adapter.DataConverter;

/**
 * @author xiaohe
 * @since 2022.03.30 11:46
 */
public interface PictureConverter extends DataConverter {

    default PictureId newId(Integer id) {
        return ifPresent(id, PictureId::new);
    }

    default PictureCategory newCategory(Integer category) {
        return ifPresent(category, PictureCategory::valueOf);
    }

    default PicturePath newCategory(String path) {
        return ifPresent(path, PicturePath::new);
    }
}
