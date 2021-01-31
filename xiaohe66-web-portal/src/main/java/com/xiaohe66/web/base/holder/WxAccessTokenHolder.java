package com.xiaohe66.web.base.holder;

import com.xiaohe66.common.net.ex.RequesterException;
import com.xiaohe66.web.code.wx.request.WxGetAccessTokenRequest;
import com.xiaohe66.web.code.wx.requester.WxGetAccessTokenRequester;
import com.xiaohe66.web.code.wx.response.WxGetAccessTokenResponse;
import com.xiaohe66.web.config.WxConfig;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;

/**
 * @author xiaohe
 * @time 2021.01.26 15:07
 */
@Slf4j
@AllArgsConstructor
public class WxAccessTokenHolder {

    private static String accessToken;

    private final WxGetAccessTokenRequester requester;
    private final WxConfig wxConfig;

    public static String get() {
        if (accessToken == null) {
            throw new IllegalStateException("cannot get accessToken");
        }
        return accessToken;
    }

    private WxGetAccessTokenResponse getAccessToken() {

        WxGetAccessTokenRequest request = new WxGetAccessTokenRequest();
        request.setAppId(wxConfig.getAppId());
        request.setSecret(wxConfig.getAppSecret());
        request.setGrantType("client_credential");

        WxGetAccessTokenResponse response;
        try {
            response = requester.call(request);
        } catch (RequesterException e) {

            log.warn("getAccessToken异常, 重试一次 , errMsg : {}", e.getMessage());
            try {
                response = requester.call(request);
            } catch (RequesterException e1) {

                log.error("getAccessToken异常，重试后仍然失败");
                return null;
            }
        }
        return response;
    }

    /**
     * 定时任务：7200秒 为 2小时，fixedRate 的值为毫秒
     *
     * <p>
     * PostConstruct 启动时执行一次
     */
    @PostConstruct
    @Scheduled(fixedDelay = 7000 * 1000)
    public void updateAccessToken() {
        log.info("update accessToken : start...");
        WxGetAccessTokenResponse response = getAccessToken();
        if (response != null) {
            WxAccessTokenHolder.accessToken = response.getAccessToken();
            log.info("update accessToken success : {}", response.getAccessToken());
        }
        log.info("update accessToken : end...");
    }

}
