package com.xiaohe66.web.code.org.helper;

import com.xiaohe66.web.base.util.Check;
import com.xiaohe66.web.code.org.dto.WxUserLoginInDto;
import org.springframework.util.DigestUtils;

/**
 * @author xiaohe
 * @time 2019.12.06 17:56
 */
public class WxUserSignHelper {

    private WxUserSignHelper() {

    }

    public static boolean verify(WxUserLoginInDto loginInDto) {

        Check.notEmpty(loginInDto.getOpenId());
        Check.notEmpty(loginInDto.getLat());
        Check.notEmpty(loginInDto.getLon());
        Check.notEmpty(loginInDto.getTimestamp());
        Check.notEmpty(loginInDto.getSign());

        String sign = sign(loginInDto);

        return sign.equals(loginInDto.getSign());
    }

    private static String sign(WxUserLoginInDto loginInDto) {

        String text = loginInDto.getOpenId() +
                loginInDto.getLat() +
                loginInDto.getLon() +
                loginInDto.getTimestamp();

        return DigestUtils.md5DigestAsHex(text.getBytes());
    }
}
