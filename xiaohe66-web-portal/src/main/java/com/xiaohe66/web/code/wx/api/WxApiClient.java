package com.xiaohe66.web.code.wx.api;

import com.xiaohe66.common.api.okhttp.AbstractOkHttpApiClient;
import com.xiaohe66.common.net.OkHttpClientHolder;
import org.springframework.stereotype.Component;

/**
 * @author xiaohe
 * @time 2021.01.06 17:06
 */
@Component
public class WxApiClient extends AbstractOkHttpApiClient {

    public WxApiClient() {
        super(OkHttpClientHolder.get(), "https://api.weixin.qq.com");
    }

}
