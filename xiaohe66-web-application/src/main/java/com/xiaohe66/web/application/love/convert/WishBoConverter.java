package com.xiaohe66.web.application.love.convert;

import com.xiaohe66.web.application.love.bo.WishSaveBo;
import com.xiaohe66.web.application.love.result.WishListResult;
import com.xiaohe66.web.application.love.result.WishResult;
import com.xiaohe66.web.domain.love.agg.Wish;
import com.xiaohe66.web.domain.love.service.LoverService;
import com.xiaohe66.web.domain.sys.sec.service.SecurityService;
import com.xiaohe66.web.infrastructure.domain.adapter.love.WishConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author xiaohe
 * @since 2022.03.29 18:29
 */
@Mapper
public abstract class WishBoConverter implements WishConverter {

    @Autowired
    protected LoverService loverService;

    @Autowired
    protected SecurityService securityService;

    public abstract WishResult toResult(Wish wish);

    public abstract WishListResult toListResult(Wish wish);

    public abstract List<WishListResult> toListResult(List<Wish> wish);

    @Mapping(target = "createId", expression = "java(securityService.getCurrentAccountId())")
    @Mapping(target = "loverId", expression = "java(loverService.getCurrentLoverId())")
    public abstract Wish toAgg(WishSaveBo bod);

}
