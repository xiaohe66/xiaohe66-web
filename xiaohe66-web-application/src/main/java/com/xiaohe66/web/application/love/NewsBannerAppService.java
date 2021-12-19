package com.xiaohe66.web.application.love;

import com.xiaohe66.common.dto.R;
import com.xiaohe66.web.application.aop.annotation.NeedRoles;
import com.xiaohe66.web.application.love.bo.NewsBannerListBo;
import com.xiaohe66.web.application.love.bo.NewsBannerSaveBo;
import com.xiaohe66.web.domain.file.value.ImageId;
import com.xiaohe66.web.domain.love.agg.NewsBanner;
import com.xiaohe66.web.domain.love.repository.NewsBannerRepository;
import com.xiaohe66.web.domain.love.service.NewsBannerService;
import com.xiaohe66.web.domain.love.value.LoverId;
import com.xiaohe66.web.domain.sys.sec.service.SecurityService;
import com.xiaohe66.web.domain.sys.sec.value.RoleName;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiaohe
 * @since 2021.12.19 12:30
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class NewsBannerAppService {

    private final NewsBannerRepository newsBannerRepository;
    private final NewsBannerService newsBannerService;
    private final SecurityService securityService;

    @NeedRoles(RoleName.LOVER_ROLE_VALUE)
    public R<Void> save(NewsBannerSaveBo bo) {

        List<ImageId> imageIdList = bo.getImageIds().stream()
                .map(ImageId::new)
                .collect(Collectors.toList());

        newsBannerService.save(imageIdList);

        return R.ok();
    }

    public R<List<String>> list(NewsBannerListBo bo) {

        LoverId loverId = new LoverId(bo.getLoverId());

        List<NewsBanner> newsBanners = newsBannerRepository.listByLoverIdAscSort(loverId);

        List<String> imageIds = newsBanners.stream()
                .map(item -> Long.toString(item.getImageId().getValue()))
                .collect(Collectors.toList());

        return R.ok(imageIds);
    }

}
