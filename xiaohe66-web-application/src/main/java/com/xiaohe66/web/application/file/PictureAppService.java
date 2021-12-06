package com.xiaohe66.web.application.file;

import com.xiaohe66.common.dto.R;
import com.xiaohe66.web.application.aop.annotation.NeedLogin;
import com.xiaohe66.web.application.file.result.PictureListResult;
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

        String directory = fileConfig.getPictureDirectory();
        File rootFile = new File(directory);

        if (!rootFile.exists()) {
            log.error("picture directory is not exist");
            throw new BusinessException(ErrorCodeEnum.ERROR);
        }

        File[] files = rootFile.listFiles();
        if (files == null) {

            log.error("rootFile.listFiles() result is null");
            throw new BusinessException(ErrorCodeEnum.ERROR);
        }

        List<PictureListResult> list = new ArrayList<>(files.length);

        for (File file : files) {
            if (file.isDirectory()) {
                PictureListResult result = find(file);
                if (result != null) {
                    list.add(result);
                }
            }
        }

        return R.ok(list);
    }

    protected PictureListResult find(File dir) {

        File[] files = dir.listFiles();
        if (files == null) {
            return null;
        }

        String dirName = dir.getName();

        List<String> images = new ArrayList<>(files.length);

        for (File file : files) {

            String path = file.getName();

            images.add(path);
        }

        PictureListResult result = new PictureListResult();

        result.setName(dirName);
        result.setImages(images);

        return result;
    }

}
