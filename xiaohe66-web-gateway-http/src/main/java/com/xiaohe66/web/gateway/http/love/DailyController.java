package com.xiaohe66.web.gateway.http.love;

import com.xiaohe66.common.dto.R;
import com.xiaohe66.web.application.love.DailyAppService;
import com.xiaohe66.web.application.love.bo.DailyListBo;
import com.xiaohe66.web.application.love.bo.DailySaveBo;
import com.xiaohe66.web.application.love.result.DailyResult;
import com.xiaohe66.web.gateway.http.love.convert.DailyDtoConverter;
import com.xiaohe66.web.gateway.http.love.dto.DailyListDto;
import com.xiaohe66.web.gateway.http.love.dto.DailySaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xiaohe
 * @since 2021.11.30 14:25
 */
@RestController
@RequestMapping("/love/daily")
@RequiredArgsConstructor
public class DailyController {

    private final DailyDtoConverter dtoConverter;
    private final DailyAppService dailyAppService;

    @PostMapping
    public R<DailyResult> save(@Validated @RequestBody DailySaveDto dto) {

        DailySaveBo bo = dtoConverter.toBo(dto);

        return dailyAppService.save(bo);
    }

    @DeleteMapping("/{id}")
    public R<Void> removeById(@PathVariable(value = "id") Long id) {

        return dailyAppService.removeById(id);
    }

    @GetMapping
    public R<List<DailyResult>> list(DailyListDto dto) {
        DailyListBo bo = dtoConverter.toBo(dto);
        return dailyAppService.listByLoverId(bo);
    }

}
