package com.xiaohe66.web.application.file.convert;

import com.xiaohe66.web.application.file.bo.ImageUploadBo;
import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.file.agg.Image;
import com.xiaohe66.web.domain.file.value.ImageId;
import com.xiaohe66.web.domain.file.value.ImageName;
import com.xiaohe66.web.integration.domain.DataConverter;
import org.mapstruct.Mapper;

/**
 * @author xiaohe
 * @since 2021.12.06 09:58
 */
@Mapper(componentModel = "spring")
public interface ImageBoConverter extends DataConverter {

    Image toAgg(ImageUploadBo bo, Long id, AccountId createId);

    default ImageId newId(Long id) {
        return ifPresent(id, ImageId::new);
    }

    default ImageName newImageName(String name) {
        return ifPresent(name, ImageName::new);
    }

}
