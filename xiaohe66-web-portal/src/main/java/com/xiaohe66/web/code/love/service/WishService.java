package com.xiaohe66.web.code.love.service;

import com.xiaohe66.web.base.base.DtoConverter;
import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.base.util.ClassUtils;
import com.xiaohe66.web.code.love.dto.WishDto;
import com.xiaohe66.web.code.love.dto.WishLogDto;
import com.xiaohe66.web.code.love.mapper.WishMapper;
import com.xiaohe66.web.code.love.po.Wish;
import com.xiaohe66.web.code.love.po.WishLog;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiaohe
 * @time 2019.10.22 11:13
 */
@Service
public class WishService extends AbstractService<WishMapper, Wish> implements DtoConverter<Wish, WishDto> {

    private WishLogService wishLogService;

    public WishService(WishLogService wishLogService) {
        this.wishLogService = wishLogService;
    }

    @Override
    public void convertDto(WishDto dto, Wish po) {

        List<WishLog> wishLogList = wishLogService.listByWishId(po.getId());
        List<WishLogDto> wishLogDtoList = ClassUtils.convert(WishLogDto.class, wishLogList);
        dto.setWishLogList(wishLogDtoList);

    }
}
