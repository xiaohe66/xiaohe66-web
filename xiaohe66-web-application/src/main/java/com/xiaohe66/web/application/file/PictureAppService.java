package com.xiaohe66.web.application.file;

import com.xiaohe66.common.dto.R;
import com.xiaohe66.web.application.aop.annotation.NeedLogin;
import com.xiaohe66.web.application.file.convert.PictureBoConverter;
import com.xiaohe66.web.application.file.result.PictureListResult;
import com.xiaohe66.web.domain.file.repository.PictureRepository;
import com.xiaohe66.web.domain.file.value.PictureCategory;
import com.xiaohe66.web.domain.file.value.PicturePath;
import com.xiaohe66.web.domain.sys.sec.service.SecurityService;
import com.xiaohe66.web.integration.config.FileConfig;
import com.xiaohe66.web.integration.ex.BusinessException;
import com.xiaohe66.web.integration.ex.ErrorCodeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaohe
 * @since 2021.12.06 11:23
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class PictureAppService {

    private final FileConfig fileConfig;
    private final PictureBoConverter boConverter;
    private final PictureRepository pictureRepository;
    private final SecurityService securityService;

    public void download(String path, OutputStream outputStream) {

        String fullPath = fileConfig.getPictureDirectory() + path;

        File file = new File(fullPath);
        if (!file.exists()) {

            log.warn("file is not exist : {}", fullPath);
            throw new BusinessException(ErrorCodeEnum.NOT_FOUND);
        }

        try (InputStream inputStream = new FileInputStream(file)) {
            IOUtils.copy(inputStream, outputStream);

        } catch (IOException e) {

            log.error("download image error, fullPath : {}", fullPath, e);
            throw new BusinessException(ErrorCodeEnum.ERROR);
        }
    }

    @NeedLogin
    public R<List<PictureListResult>> list() {

        List<PictureListResult> list = new ArrayList<>(5);

        list.add(getResult(PictureCategory.ANCIENT, "古装,不一样的韵味"));
        list.add(getResult(PictureCategory.BIG, "婚纱,你要的仪式感"));
        list.add(getResult(PictureCategory.RED, "浪漫,法国味"));
        list.add(getResult(PictureCategory.SMALL, "小清新,花美你更美"));
        list.add(getResult(PictureCategory.STUDENT, "学生,从校服到婚纱"));

        return R.ok(list);
    }

    private PictureListResult getResult(PictureCategory category, String name) {
        List<PicturePath> pathList = pictureRepository.listByCategory(category);

        PictureListResult result = new PictureListResult();
        result.setImages(boConverter.toBo(pathList));
        result.setName(name);

        return result;
    }

    protected PictureListResult find(File dir) {

        File[] files = dir.listFiles();
        if (files == null) {
            return null;
        }

        String dirName = dir.getName();

        List<String> images = new ArrayList<>(files.length);

        for (File file : files) {

            if (file.isFile()) {
                String path = file.getName();

                images.add(path);
            }
        }

        PictureListResult result = new PictureListResult();

        result.setName(dirName);
        result.setImages(images);

        return result;
    }

}
