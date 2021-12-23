package com.xiaohe66.web.infrastructure.mybatis.file.image.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xiaohe66.web.domain.file.agg.Picture;
import com.xiaohe66.web.domain.file.repository.PictureRepository;
import com.xiaohe66.web.domain.file.value.PictureCategory;
import com.xiaohe66.web.domain.file.value.PictureId;
import com.xiaohe66.web.domain.file.value.PicturePath;
import com.xiaohe66.web.infrastructure.mybatis.file.image.convert.PictureDoConverter;
import com.xiaohe66.web.infrastructure.mybatis.file.image.mapper.PictureMapper;
import com.xiaohe66.web.infrastructure.mybatis.file.image.model.PictureDo;
import com.xiaohe66.web.integration.AbstractMybatisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiaohe
 * @since 2021.12.23 21:31
 */
@Repository
@Slf4j
@RequiredArgsConstructor
public class PictureRepositoryImpl
        extends AbstractMybatisService<PictureDoConverter, PictureMapper, PictureDo, Picture, PictureId>
        implements PictureRepository {

    @Override
    public List<PicturePath> listByCategory(PictureCategory category) {

        LambdaQueryWrapper<PictureDo> queryWrapper = new LambdaQueryWrapper<PictureDo>()
                .eq(PictureDo::getCategory, category.getValue());

        List<PictureDo> list = list(queryWrapper);

        return dataConverter.toPathList(list);
    }
}
