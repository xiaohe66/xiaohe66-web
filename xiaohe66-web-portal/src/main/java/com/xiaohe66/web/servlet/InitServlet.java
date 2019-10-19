package com.xiaohe66.web.servlet;

import com.xiaohe66.web.code.sys.service.EmailService;
import com.xiaohe66.web.code.sys.service.SysCfgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 初始化
 *
 * @author xh
 * @version 1.0
 * @time 2018-07-24 10:50
 */
//@Component
public class InitServlet implements InitializingBean {
    private static final Logger LOG = LoggerFactory.getLogger(InitServlet.class);

    private final SysCfgService cfgService;
    private final EmailService emailService;

    @Autowired
    public InitServlet(SysCfgService cfgService, EmailService emailService) {
        this.cfgService = cfgService;
        this.emailService = emailService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        LOG.info("系统初始化……");

        LOG.info("加载系统配置");
//        SysCfgHelper.refresh(cfgService.findAll());

        LOG.info("初始化邮箱助手");
//        EmailHelper.init(emailService);

        LOG.info("系统初始化完成！");
    }
}
