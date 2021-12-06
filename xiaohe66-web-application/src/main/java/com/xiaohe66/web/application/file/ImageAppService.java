package com.xiaohe66.web.application.file;

import com.xiaohe66.common.dto.R;
import com.xiaohe66.common.util.IdWorker;
import com.xiaohe66.web.application.aop.annotation.NeedLogin;
import com.xiaohe66.web.application.aop.annotation.NeedRoles;
import com.xiaohe66.web.application.file.bo.ImageUploadBo;
import com.xiaohe66.web.application.file.convert.ImageBoConverter;
import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.file.agg.Image;
import com.xiaohe66.web.domain.file.repository.ImageRepository;
import com.xiaohe66.web.domain.file.service.ImageService;
import com.xiaohe66.web.domain.file.value.ImageId;
import com.xiaohe66.web.domain.sys.sec.service.SecurityService;
import com.xiaohe66.web.integration.ex.BusinessException;
import com.xiaohe66.web.integration.ex.ErrorCodeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author xiaohe
 * @since 2021.12.03 10:47
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ImageAppService {

    private final ImageBoConverter boConverter;
    private final ImageRepository imageRepository;
    private final ImageService imageService;
    private final SecurityService securityService;

    @NeedRoles("love")
    public R<Long> upload(ImageUploadBo bo) {

        long id = IdWorker.genId();

        AccountId currentAccountId = securityService.getCurrentAccountId();

        Image image = boConverter.toAgg(bo, id, currentAccountId);
        imageService.save(image);

        return R.ok(id);
    }

    @NeedLogin
    public void download(Long idValue, OutputStream outputStream) {

        ImageId id = new ImageId(idValue);

        Image image = imageRepository.getById(id);

        try (InputStream inputStream = image.getInputStream()) {

            IOUtils.copy(inputStream, outputStream);

        } catch (IOException e) {
            log.error("download image error, id : {}", id, e);
            throw new BusinessException(ErrorCodeEnum.ERROR);
        }
    }

}
