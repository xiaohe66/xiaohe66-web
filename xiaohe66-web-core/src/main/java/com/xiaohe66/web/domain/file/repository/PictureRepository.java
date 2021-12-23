package com.xiaohe66.web.domain.file.repository;


import com.xiaohe66.web.domain.file.agg.Picture;
import com.xiaohe66.web.domain.file.value.PictureCategory;
import com.xiaohe66.web.domain.file.value.PictureId;
import com.xiaohe66.web.domain.file.value.PicturePath;
import com.xiaohe66.web.integration.domain.Repository;

import java.util.List;

/**
 * @author xiaohe
 * @since 2021.12.23 21:19
 */
public interface PictureRepository extends Repository<Picture, PictureId> {

    List<PicturePath> listByCategory(PictureCategory category);

}
