package com.xiaohe66.web.infrastructure.mybatis.file.image.convert;

import com.xiaohe66.common.util.ex.BusinessException;
import com.xiaohe66.common.util.ex.ErrorCodeEnum;
import com.xiaohe66.web.domain.file.agg.Image;
import com.xiaohe66.web.domain.file.value.ImageContext;
import com.xiaohe66.web.domain.file.value.ImageId;
import com.xiaohe66.web.domain.file.value.ImageName;
import com.xiaohe66.web.infrastructure.mybatis.file.image.model.ImageDo;
import com.xiaohe66.web.integration.DoConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author xiaohe
 * @since 2021.12.03 17:15
 */
@Mapper(componentModel = "spring")
public interface ImageDoConverter extends DoConverter<Image, ImageDo> {

    Logger log = LoggerFactory.getLogger(ImageDoConverter.class);

    @Mapping(target = "size", expression = "java(agg.getContext().size())")
    @Mapping(target = "md5", source = "context.md5")
    @Override
    ImageDo toDo(Image agg);

    @Mapping(target = "context", source = "fullPath")
    Image toAgg(ImageDo imageDo, String fullPath);

    default ImageContext newImageContext(String fullPath) {

        try (FileInputStream fileInputStream = new FileInputStream(fullPath)) {

            return new ImageContext(fileInputStream);

        } catch (IOException e) {

            log.error("cannot read file, fullPath : {}", fullPath, e);
            throw new BusinessException(ErrorCodeEnum.ERROR);
        }
    }

    default ImageId newId(Long id) {
        return ifPresent(id, ImageId::new);
    }

    default ImageName newImage(String name) {
        return ifPresent(name, ImageName::new);
    }

}
