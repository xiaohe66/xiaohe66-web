package com.xiaohe66.web.application.love;

import com.xiaohe66.common.dto.R;
import com.xiaohe66.common.util.IdWorker;
import com.xiaohe66.web.application.aop.annotation.NeedRoles;
import com.xiaohe66.web.application.love.bo.WishListBo;
import com.xiaohe66.web.application.love.bo.WishSaveBo;
import com.xiaohe66.web.application.love.convert.WishBoConverter;
import com.xiaohe66.web.application.love.result.WishListResult;
import com.xiaohe66.web.application.love.result.WishResult;
import com.xiaohe66.web.domain.love.agg.Wish;
import com.xiaohe66.web.domain.love.repository.WishRepository;
import com.xiaohe66.web.domain.love.service.LoverService;
import com.xiaohe66.web.domain.love.service.WishService;
import com.xiaohe66.web.domain.love.value.LoverId;
import com.xiaohe66.web.domain.love.value.WishFinished;
import com.xiaohe66.web.domain.love.value.WishId;
import com.xiaohe66.web.domain.sys.sec.value.RoleName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiaohe
 * @since 2022.03.29 18:14
 */
@Service
@RequiredArgsConstructor
public class WishAppService {

    private final WishBoConverter boConverter;
    private final WishRepository wishRepository;
    private final WishService wishService;

    private final LoverService loverService;

    @NeedRoles({RoleName.LOVER_ROLE_VALUE})
    public R<WishResult> create(WishSaveBo bo) {

        bo.setId(IdWorker.genId());

        Wish wish = boConverter.toAgg(bo);

        wishRepository.save(wish);

        Wish dbWish = wishRepository.getById(wish.getId());

        return R.ok(boConverter.toResult(dbWish));
    }

    @NeedRoles({RoleName.LOVER_ROLE_VALUE})
    public R<WishResult> modify(WishSaveBo bo) {

        Wish wish = boConverter.toAgg(bo);

        wishService.update(wish);

        Wish dbWish = wishRepository.getById(wish.getId());

        return R.ok(boConverter.toResult(dbWish));
    }

    @NeedRoles({RoleName.LOVER_ROLE_VALUE})
    public R<List<WishListResult>> list(WishListBo bo) {

        LoverId currentLoverId = loverService.getCurrentLoverId();

        WishFinished wishFinished = WishFinished.valueOf(bo.getFinished());

        List<Wish> list = wishService.list(currentLoverId, wishFinished, bo.toPaging());

        List<WishListResult> result = boConverter.toListResult(list);

        return R.ok(result);
    }

    @NeedRoles({RoleName.LOVER_ROLE_VALUE})
    public WishResult detail(Long idValue) {

        WishId id = new WishId(idValue);

        Wish wish = wishService.detail(id);

        return boConverter.toResult(wish);
    }

}
