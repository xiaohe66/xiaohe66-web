package com.xiaohe66.web.servlet;

import com.xiaohe66.web.sys.service.SysCfgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 初始化
 *
 * @author xh
 * @version 1.0
 * @time 2018-07-24 10:50
 */
@Component
public class InitServlet implements InitializingBean{
    private static final Logger LOG = LoggerFactory.getLogger(InitServlet.class);

    private final SysCfgService sysCfgService;

    @Autowired
    public InitServlet(SysCfgService sysCfgService) {
        this.sysCfgService = sysCfgService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        LOG.info("系统初始化……");

        sysCfgService.refresh();

        LOG.info("系统初始化完成！");
    }
}
