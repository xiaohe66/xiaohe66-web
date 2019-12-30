package com.xiaohe66.web.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author xiaohe
 * @time 2019.12.30 17:43
 */
@ConfigurationProperties(prefix = "xiaohe66.file")
@Component
@Data
public class FileConfig {

    private String home;
    private Integer maxMbChunkPer;
    private Integer maxMbFilePer;
}
