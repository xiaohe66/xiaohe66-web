package com.xiaohe66.web.domain.file.service;

import com.xiaohe66.web.domain.file.agg.Image;
import com.xiaohe66.web.domain.file.repository.ImageRepository;
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

    public void save(Image image){
        imageRepository.save(image);
    }

}
