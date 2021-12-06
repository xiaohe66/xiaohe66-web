package com.xiaohe66.web.infrastructure.mybatis.file.image.convert;

import com.xiaohe66.web.domain.file.agg.Image;
import com.xiaohe66.web.domain.file.value.ImageId;
import com.xiaohe66.web.domain.file.value.ImageName;
import com.xiaohe66.web.infrastructure.mybatis.file.image.model.ImageDo;
import com.xiaohe66.web.integration.DoConverter;
import org.mapstruct.Mapper;

import java.io.InputStream;

/**
 * @author xiaohe
 * @since 2021.12.03 17:15
 */
@Mapper(componentModel = "spring")
public interface ImageDoConverter extends DoConverter<Image, ImageDo> {

    Image toAgg(ImageDo imageDo, InputStream inputStream);

    default ImageId newId(Long id) {
        return ifPresent(id, ImageId::new);
    }

    default ImageName newImage(String name) {
        return ifPresent(name, ImageName::new);
    }

}
