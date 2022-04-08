package com.xiaohe66.web.gateway.http.love;

import com.xiaohe66.common.dto.R;
import com.xiaohe66.common.util.Assert;
import com.xiaohe66.web.application.love.WishAppService;
import com.xiaohe66.web.application.love.bo.WishListBo;
import com.xiaohe66.web.application.love.bo.WishSaveBo;
import com.xiaohe66.web.application.love.result.WishResult;
import com.xiaohe66.web.gateway.http.love.convert.WishDtoConverter;
import com.xiaohe66.web.gateway.http.love.dto.WishListDto;
import com.xiaohe66.web.gateway.http.love.dto.WishSaveDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaohe
 * @since 2022.03.29 16:14
 */
@RestController
@RequestMapping("/love/wish")
@RequiredArgsConstructor
@Slf4j
public class WishController {

    private final WishDtoConverter dtoConverter;
    private final WishAppService appService;

    @PostMapping
    public R<?> save(@Validated @RequestBody WishSaveDto dto) {

        WishSaveBo bo = dtoConverter.toBo(dto);
        return appService.create(bo);
    }

    @PutMapping
    public R<?> update(@Validated @RequestBody WishSaveDto dto) {

        Assert.notNull(dto.getId());

        WishSaveBo bo = dtoConverter.toBo(dto);
        return appService.modify(bo);
    }

    @GetMapping
    public R<?> list(@Validated WishListDto dto) {

        WishListBo bo = dtoConverter.toBo(dto);

        return appService.list(bo);
    }

    @GetMapping("/{id}")
    public R<?> detail(@PathVariable Long id) {

        WishResult result = appService.detail(id);

        return R.ok(result);
    }
}
