package com.xiaohe66.web.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author xiaohe
 * @time 2019.12.30 18:01
 */
@ConfigurationProperties(prefix = "xiaohe66")
@Component
@Data
public class MainConfig {

    private String registerUrl;

}
