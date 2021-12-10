package com.xiaohe66.web.domain.file.repository;

import com.xiaohe66.web.domain.file.agg.Image;
import com.xiaohe66.web.domain.file.value.ImageId;
import com.xiaohe66.web.integration.domain.Md5;
import com.xiaohe66.web.integration.domain.Repository;

/**
 * @author xiaohe
 * @since 2021.12.03 10:48
 */
public interface ImageRepository extends Repository<Image, ImageId> {

    Image getByMd5(Md5 md5);

}
