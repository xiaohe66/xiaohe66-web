package com.xiaohe66.web.gateway.http.account;

import com.xiaohe66.common.dto.R;
import com.xiaohe66.web.application.account.WxUserAppService;
import com.xiaohe66.web.application.account.bo.WxUserUpdateBo;
import com.xiaohe66.web.gateway.http.account.convert.WxUserDtoConverter;
import com.xiaohe66.web.gateway.http.account.dto.WxUserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xiaohe
 * @since 2021.12.07 13:52
 */
@RestController
@RequestMapping("/wx/user")
@RequiredArgsConstructor
public class WxUserController {

    private final WxUserDtoConverter dtoConverter;
    private final WxUserAppService wxUserAppService;

    @PutMapping
    public R<Void> update(@Validated @RequestBody WxUserUpdateDto dto) {
        WxUserUpdateBo bo = dtoConverter.toBo(dto);
        return wxUserAppService.updateCurrent(bo);
    }

    @GetMapping("/avatar/{accountId}")
    public void downloadAvatar(@PathVariable("accountId") Long accountId,
                               HttpServletResponse response) throws IOException {

        response.setContentType("image/png");

        wxUserAppService.downloadAvatar(accountId, response.getOutputStream());
    }
}
