package com.xiaohe66.web.servlet;

import com.xiaohe66.web.sys.service.EmailService;
import com.xiaohe66.web.sys.helper.SysCfgHelper;
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

    private final SysCfgHelper cfgHelper;
    private final EmailService emailService;

    @Autowired
    public InitServlet(SysCfgHelper cfgHelper, EmailService emailService) {
        this.cfgHelper = cfgHelper;
        this.emailService = emailService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        LOG.info("系统初始化……");

        LOG.info("加载系统配置");
        cfgHelper.refresh();

        LOG.info("初始化邮箱服务");
        emailService.init();

        LOG.info("系统初始化完成！");
    }
}
