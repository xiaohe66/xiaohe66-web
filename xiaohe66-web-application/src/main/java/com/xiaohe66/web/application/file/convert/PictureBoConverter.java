package com.xiaohe66.web.application.file.convert;

import com.xiaohe66.web.domain.file.value.PicturePath;
import com.xiaohe66.web.integration.domain.DataConverter;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author xiaohe
 * @since 2021.12.23 21:22
 */
@Mapper(componentModel = "spring")
public interface PictureBoConverter extends DataConverter {

    List<String> toBo(List<PicturePath> list);

}
