package com.xiaohe66.web.config;

import com.xiaohe66.web.base.base.CreateTableMapper;
import com.xiaohe66.web.base.holder.ApplicationContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
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

        log.info("开始创建表");
        createTable();
        log.info("创建表完成");

        log.info("系统启动完成");
    }

    private void createTable() {

        ApplicationContext context = ApplicationContextHolder.get();
        String profile = context.getEnvironment().getActiveProfiles()[0];

        if ("dev".equals(profile)) {

            log.debug("开发环境不自动创建表");
            return;
        }

        Map<String, CreateTableMapper> beans = context.getBeansOfType(CreateTableMapper.class);

        for (Map.Entry<String, CreateTableMapper> entry : beans.entrySet()) {
            String mapperName = entry.getKey();

            log.info("调用{}创建表", mapperName);
            CreateTableMapper mapper = entry.getValue();
            try {
                mapper.createTable();

            } catch (Exception e) {
                log.error("调用时发生异常, mapperName : {}, message : {}", mapperName, e.getMessage());
            }
        }

    }

}
