package com.xiaohe66.web.application.file;

import com.xiaohe66.common.dto.R;
import com.xiaohe66.web.application.aop.annotation.NeedRoles;
import com.xiaohe66.web.application.file.bo.ImageUploadBo;
import com.xiaohe66.web.application.file.convert.ImageBoConverter;
import com.xiaohe66.web.domain.file.agg.Image;
import com.xiaohe66.web.domain.file.repository.ImageRepository;
import com.xiaohe66.web.domain.file.service.ImageService;
import com.xiaohe66.web.domain.file.value.ImageContext;
import com.xiaohe66.web.domain.file.value.ImageId;
import com.xiaohe66.web.domain.sys.sec.service.SecurityService;
import com.xiaohe66.web.integration.ex.BusinessException;
import com.xiaohe66.web.integration.ex.ErrorCodeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
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

        ImageContext context;
        try {
            context = new ImageContext(bo.getInputStream());

        } catch (IOException e) {
            log.error("read input error", e);
            throw new BusinessException(ErrorCodeEnum.ERROR);
        }
        Image image = imageService.save(context);

        return R.ok(image.getId().getValue());
    }

    public void download(Long idValue, OutputStream outputStream) {

        ImageId id = new ImageId(idValue);

        Image image = imageRepository.getById(id);

        if (image == null) {
            throw new BusinessException(ErrorCodeEnum.NOT_FOUND);
        }

        ImageContext context = image.getContext();

        try {
            context.write(outputStream);

        } catch (IOException e) {
            log.error("download image error, id : {}", id, e);
            throw new BusinessException(ErrorCodeEnum.ERROR);
        }
    }

}
