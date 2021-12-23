package com.xiaohe66.web.infrastructure.mybatis.file.image.convert;

import com.xiaohe66.web.domain.file.agg.Picture;
import com.xiaohe66.web.domain.file.value.PictureCategory;
import com.xiaohe66.web.domain.file.value.PictureId;
import com.xiaohe66.web.domain.file.value.PicturePath;
import com.xiaohe66.web.infrastructure.mybatis.file.image.model.PictureDo;
import com.xiaohe66.web.integration.DoConverter;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiaohe
 * @since 2021.12.23 21:30
 */
@Mapper(componentModel = "spring")
public abstract class PictureDoConverter implements DoConverter<Picture, PictureDo> {

    public List<PicturePath> toPathList(List<PictureDo> list) {

        return list.stream()
                .map(PictureDo::getPath)
                .map(PicturePath::new)
                .collect(Collectors.toList());

    }

    protected PictureId newId(Integer id) {
        return ifPresent(id, PictureId::new);
    }

    protected PictureCategory newCategory(Integer category) {
        return ifPresent(category, PictureCategory::valueOf);
    }

    protected PicturePath newCategory(String path) {
        return ifPresent(path, PicturePath::new);
    }

}
