package com.xiaohe66.web.domain.file.service;

import com.xiaohe66.common.util.IdWorker;
import com.xiaohe66.web.domain.file.agg.Image;
import com.xiaohe66.web.domain.file.repository.ImageRepository;
import com.xiaohe66.web.domain.file.value.ImageContext;
import com.xiaohe66.web.domain.file.value.ImageId;
import com.xiaohe66.web.domain.sys.sec.service.SecurityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author xiaohe
 * @since 2021.12.02 18:29
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final SecurityService securityService;

    public Image save(ImageContext context) {

        Image dbImage = imageRepository.getByMd5(context.getMd5());
        if (dbImage != null) {
            return dbImage;
        }

        Image image = Image.builder()
                .id(new ImageId(IdWorker.genId()))
                .createId(securityService.getCurrentAccountId())
                .context(context)
                .build();

        imageRepository.save(image);

        return image;
    }

}
