package com.xiaohe66.web.gateway.http.love;

import com.xiaohe66.common.dto.R;
import com.xiaohe66.web.application.love.LoverAppService;
import com.xiaohe66.web.application.love.result.LoverInfoResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaohe
 * @since 2020.01.06 17:01
 */
@RestController
@RequestMapping("/love/lover")
@RequiredArgsConstructor
public class LoverController {

    private final LoverAppService loverAppService;

    @PostMapping("/ready")
    public R<String> ready() {

        return loverAppService.readyBind();
    }

    @PostMapping("/{serialNo}")
    public R<LoverInfoResult> binding(@PathVariable String serialNo) {
        return loverAppService.binding(serialNo);
    }

    @PostMapping("/confirm")
    public R<Void> confirm() {

        return loverAppService.confirmBind();
    }

    @GetMapping("/info")
    public R<LoverInfoResult> getLoverInfo() {

        return loverAppService.info();
    }

}
