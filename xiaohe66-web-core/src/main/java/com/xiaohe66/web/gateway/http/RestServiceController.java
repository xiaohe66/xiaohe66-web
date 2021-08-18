package com.xiaohe66.web.gateway.http;

import com.xiaohe66.web.domain.rest.service.RestService;
import com.xiaohe66.web.integration.wrap.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author xiaohe
 * @since 2021.08.13 14:45
 */
@RestController
@RequiredArgsConstructor
public class RestServiceController {

    private final RestService restService;

    @PostMapping("/{tableAlias}")
    public R<Long> post(@PathVariable String tableAlias, Map<String, Object> data) {
        Long id = restService.save(tableAlias, data);
        return R.ok(id);
    }

    @DeleteMapping("/{tableAlias}/{id}")
    public R<Void> delete(@PathVariable String tableAlias, @PathVariable("id") long id) {
        restService.remove(tableAlias, id);
        return R.ok();
    }

    @PutMapping("/{tableAlias}")
    public R<Void> put(@PathVariable String tableAlias, Map<String, Object> data) {
        restService.modifyById(tableAlias, data);
        return R.ok();
    }

    @GetMapping("/{tableAlias}/{id}")
    public R<Object> get(@PathVariable String tableAlias, @PathVariable("id") long id) {
        Object object = restService.getById(tableAlias, id);
        return R.ok(object);
    }

    @GetMapping("/{tableAlias}")
    public R<Object> list(@RequestHeader(value = "pageSize", required = false, defaultValue = "15") long pageSize,
                          @RequestHeader(value = "pageNo", required = false, defaultValue = "1") long pageNo,
                          @RequestHeader(value = "startId", required = false, defaultValue = "1") long startId,
                          @PathVariable String tableAlias,
                          Map<String, Object> query) {

        // todo : impl start
        Object object = restService.page(tableAlias, pageNo, pageSize);

        return R.ok(object);
    }
}
