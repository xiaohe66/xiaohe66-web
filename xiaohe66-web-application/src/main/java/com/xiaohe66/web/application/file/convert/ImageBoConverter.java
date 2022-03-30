package com.xiaohe66.web.application.file.convert;

import com.xiaohe66.web.application.file.bo.ImageUploadBo;
import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.file.agg.Image;
import com.xiaohe66.web.infrastructure.domain.adapter.file.ImageConverter;
import org.mapstruct.Mapper;

/**
 * @author xiaohe
 * @since 2021.12.06 09:58
 */
@Mapper(componentModel = "spring")
public interface ImageBoConverter extends ImageConverter {

    Image toAgg(ImageUploadBo bo, Long id, AccountId createId);

}
