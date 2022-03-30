package com.xiaohe66.web.infrastructure.mybatis.file.image.convert;

import com.xiaohe66.common.util.ex.BusinessException;
import com.xiaohe66.common.util.ex.ErrorCodeEnum;
import com.xiaohe66.web.domain.file.agg.Image;
import com.xiaohe66.web.domain.file.value.ImageContext;
import com.xiaohe66.web.infrastructure.domain.adapter.file.ImageConverter;
import com.xiaohe66.web.infrastructure.mybatis.file.image.model.ImageDo;
import com.xiaohe66.web.integration.DoConverter;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author xiaohe
 * @since 2021.12.03 17:15
 */
@Mapper
@Slf4j
public abstract class ImageDoConverter implements DoConverter<Image, ImageDo>, ImageConverter {

    @Mapping(target = "size", expression = "java(agg.getContext().size())")
    @Mapping(target = "md5", source = "context.md5")
    @Override
    public abstract ImageDo toDo(Image agg);

    @Mapping(target = "context", source = "fullPath")
    public abstract Image toAgg(ImageDo imageDo, String fullPath);

    public ImageContext newImageContext(String fullPath) {

        try (FileInputStream fileInputStream = new FileInputStream(fullPath)) {

            return new ImageContext(fileInputStream);

        } catch (IOException e) {

            log.error("cannot read file, fullPath : {}", fullPath, e);
            throw new BusinessException(ErrorCodeEnum.ERROR);
        }
    }

}
