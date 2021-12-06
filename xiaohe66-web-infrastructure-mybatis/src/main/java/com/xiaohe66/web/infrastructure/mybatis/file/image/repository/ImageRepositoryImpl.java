package com.xiaohe66.web.infrastructure.mybatis.file.image.repository;

import com.xiaohe66.web.domain.file.agg.Image;
import com.xiaohe66.web.domain.file.repository.ImageRepository;
import com.xiaohe66.web.domain.file.value.ImageId;
import com.xiaohe66.web.infrastructure.mybatis.file.image.convert.ImageDoConverter;
import com.xiaohe66.web.infrastructure.mybatis.file.image.mapper.ImageMapper;
import com.xiaohe66.web.infrastructure.mybatis.file.image.model.ImageDo;
import com.xiaohe66.web.integration.AbstractMybatisService;
import com.xiaohe66.web.integration.config.FileConfig;
import com.xiaohe66.web.integration.ex.BusinessException;
import com.xiaohe66.web.integration.ex.ErrorCodeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author xiaohe
 * @since 2021.12.03 17:16
 */
@Repository
@RequiredArgsConstructor
@Slf4j
public class ImageRepositoryImpl
        extends AbstractMybatisService<ImageDoConverter, ImageMapper, ImageDo, Image, ImageId>
        implements ImageRepository {

    private final FileConfig fileConfig;

    @Override
    protected void insertImpl(Image agg) {

        String fullPath = getFullPath(agg);

        File file = new File(fullPath);

        try {
            FileUtils.touch(file);
        } catch (IOException e) {
            log.error("create file error, image : {}", agg, e);
            throw new BusinessException(ErrorCodeEnum.ERROR);
        }

        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            IOUtils.copy(agg.getInputStream(), fileOutputStream);

        } catch (IOException e) {

            log.error("save image error, image : {}", agg, e);
            throw new BusinessException(ErrorCodeEnum.ERROR);
        }

        super.insertImpl(agg);
    }

    @Override
    protected void updateImpl(Image agg, Image snapshot) {
        throw new UnsupportedOperationException("cannot update, image : " + agg);
    }

    @Override
    protected Image getByIdImpl(ImageId id) {

        ImageDo imageDo = getById(id.getValue());
        if (imageDo == null) {
            return null;
        }

        String fullPath = getFullPath(id);

        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(fullPath);

        } catch (FileNotFoundException e) {
            log.error("cannot found file, image : {}", imageDo, e);
            throw new BusinessException(ErrorCodeEnum.ERROR);
        }

        return dataConverter.toAgg(imageDo, fileInputStream);
    }

    private String getFullPath(Image image) {
        return fileConfig.getImageDirectory() + image.getAbsolutePath();
    }

    private String getFullPath(ImageId id) {
        return fileConfig.getImageDirectory() + id.takeAbsolutePath();
    }
}
