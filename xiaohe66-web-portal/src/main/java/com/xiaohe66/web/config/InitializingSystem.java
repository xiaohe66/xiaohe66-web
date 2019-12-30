package com.xiaohe66.web.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author xiaohe
 * @time 2019.12.30 11:40
 */
@Component
@Slf4j
public class InitializingSystem implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        log.info("系统启动完成");
    }

}
