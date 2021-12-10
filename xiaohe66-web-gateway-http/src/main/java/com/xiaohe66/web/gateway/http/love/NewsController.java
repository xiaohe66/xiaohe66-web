package com.xiaohe66.web.gateway.http.love;

import com.xiaohe66.common.dto.R;
import com.xiaohe66.web.application.love.NewsAppService;
import com.xiaohe66.web.application.love.bo.NewsListBo;
import com.xiaohe66.web.application.love.bo.NewsSaveBo;
import com.xiaohe66.web.application.love.result.NewsResult;
import com.xiaohe66.web.gateway.http.love.convert.NewsDtoConverter;
import com.xiaohe66.web.gateway.http.love.dto.NewsListDto;
import com.xiaohe66.web.gateway.http.love.dto.NewsSaveDto;
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
 * @since 2021.12.01 17:53
 */
@RestController
@RequestMapping("/love/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsDtoConverter dtoConverter;
    private final NewsAppService newsAppService;

    @PostMapping
    public R<NewsResult> save(@Validated @RequestBody NewsSaveDto dto) {
        NewsSaveBo bo = dtoConverter.toBo(dto);
        return newsAppService.save(bo);
    }

    @DeleteMapping("/{id}")
    public R<Void> removeById(@PathVariable("id") Long id) {
        return newsAppService.removeById(id);
    }

    @GetMapping
    public R<List<NewsResult>> listByLoverId(@Validated NewsListDto dto) {

        NewsListBo bo = dtoConverter.toBo(dto);

        return newsAppService.listByLoverIdDesc(bo);
    }

}
