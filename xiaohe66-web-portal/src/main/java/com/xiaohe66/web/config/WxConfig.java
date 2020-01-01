package com.xiaohe66.web.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author xiaohe
 * @time 2019.12.30 17:54
 */
@ConfigurationProperties(prefix = "xiaohe66.wx")
@Component
@Data
public class WxConfig {

    private String code2SessionUrl;
    private String appId;
    private String appSecret;

}
