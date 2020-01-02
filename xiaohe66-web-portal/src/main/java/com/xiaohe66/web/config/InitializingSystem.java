package com.xiaohe66.web.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 *
 * 实现 {@link ApplicationRunner} 接口，使其在项目启动完成的立即运行
 *
 * @author xiaohe
 * @time 2019.12.30 11:40
 */
@Component
@Slf4j
public class InitializingSystem implements ApplicationRunner {

    private final MainConfig mainConfig;
    private final FileConfig fileConfig;
    private final WxConfig wxConfig;

    @Autowired
    public InitializingSystem(MainConfig mainConfig, FileConfig fileConfig, WxConfig wxConfig) {
        this.mainConfig = mainConfig;
        this.fileConfig = fileConfig;
        this.wxConfig = wxConfig;
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        log.info("获取到参数:");
        log.info("mainConfig : {}", mainConfig);
        log.info("fileConfig : {}", fileConfig);
        log.info("wxConfig : {}", wxConfig);
        log.info("系统启动完成");
    }

}
