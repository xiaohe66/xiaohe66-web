package com.xiaohe66.web.domain.love.service;

import com.xiaohe66.common.util.Assert;
import com.xiaohe66.common.util.IdWorker;
import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.file.repository.ImageRepository;
import com.xiaohe66.web.domain.file.value.ImageId;
import com.xiaohe66.web.domain.love.agg.NewsBanner;
import com.xiaohe66.web.domain.love.repository.NewsBannerRepository;
import com.xiaohe66.web.domain.love.value.LoverId;
import com.xiaohe66.web.domain.love.value.NewsBannerId;
import com.xiaohe66.web.domain.love.value.NewsBannerSort;
import com.xiaohe66.web.domain.sys.sec.service.SecurityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author xiaohe
 * @since 2021.12.19 12:24
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class NewsBannerService {

    private final NewsBannerRepository newsBannerRepository;
    private final ImageRepository imageRepository;
    private final SecurityService securityService;
    private final LoverService loverService;

    @Transactional(rollbackFor = Exception.class)
    public void save(List<ImageId> imageIdList) {

        LoverId loverId = loverService.getCurrentLoverId();
        AccountId currentAccountId = securityService.getCurrentAccountId();

        List<NewsBanner> oldBannerList = newsBannerRepository.listByLoverIdAscSort(loverId);
        List<NewsBanner> newBannerList = new ArrayList<>(imageIdList.size());

        for (int i = 0; i < imageIdList.size(); i++) {

            ImageId imageId = imageIdList.get(i);

            NewsBanner oldBanner = findAndRemoveImage(oldBannerList, imageId);

            if (oldBanner != null) {
                oldBanner.move(NewsBannerSort.valueOf(i));
                newBannerList.add(oldBanner);

            } else {

                Assert.requireTrue(imageRepository.isExist(imageId));

                NewsBanner newBanner = NewsBanner.builder()
                        .id(new NewsBannerId(IdWorker.genId()))
                        .createId(currentAccountId)
                        .loverId(loverId)
                        .imageId(imageId)
                        .sort(NewsBannerSort.valueOf(i))
                        .build();

                newBannerList.add(newBanner);
            }
        }

        // 要删除掉的部分
        for (NewsBanner newsBanner : oldBannerList) {
            newsBannerRepository.removeById(newsBanner.getId());
        }

        // 保存的部分
        for (NewsBanner newsBanner : newBannerList) {
            newsBannerRepository.save(newsBanner);
        }
    }

    /**
     * 从列表中找相同 imageId的对象，找到就返回对应对象并从列表中删除掉，找不到返回null
     */
    protected NewsBanner findAndRemoveImage(List<NewsBanner> list, ImageId imageId) {

        Iterator<NewsBanner> iterator = list.iterator();

        while (iterator.hasNext()) {

            NewsBanner newsBanner = iterator.next();

            if (imageId.equals(newsBanner.getImageId())) {
                iterator.remove();
                return newsBanner;
            }
        }
        return null;
    }
}


