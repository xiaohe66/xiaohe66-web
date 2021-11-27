package com.xiaohe66.web.integration.config;

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

    private Prop task;
    private Prop love;

    @Data
    public static class Prop {
        private String miniProgramState;
        private String appId;
        private String appSecret;
    }

}
