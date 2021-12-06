package com.xiaohe66.web.integration.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author xiaohe
 * @since 2021.12.03 17:18
 */
@ConfigurationProperties(prefix = "xiaohe66.file")
@Component
@Data
public class FileConfig {

    private String imageDirectory;

}
