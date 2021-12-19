package com.xiaohe66.web.gateway.http.love;

import com.xiaohe66.common.dto.R;
import com.xiaohe66.web.application.love.NewsBannerAppService;
import com.xiaohe66.web.application.love.bo.NewsBannerListBo;
import com.xiaohe66.web.application.love.bo.NewsBannerSaveBo;
import com.xiaohe66.web.gateway.http.love.convert.NewsBannerDtoConverter;
import com.xiaohe66.web.gateway.http.love.dto.NewsBannerListDto;
import com.xiaohe66.web.gateway.http.love.dto.NewsBannerSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xiaohe
 * @since 2021.12.19 13:29
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/love/news/banner")
public class NewsBannerController {

    private final NewsBannerDtoConverter dtoConverter;
    private final NewsBannerAppService newsBannerAppService;

    @PostMapping
    public R<Void> save(@Validated @RequestBody NewsBannerSaveDto dto) {

        NewsBannerSaveBo bo = dtoConverter.toBo(dto);
        return newsBannerAppService.save(bo);
    }

    @GetMapping
    public R<List<String>> get(@Validated NewsBannerListDto dto) {

        NewsBannerListBo bo = dtoConverter.toBo(dto);

        return newsBannerAppService.list(bo);
    }

}
