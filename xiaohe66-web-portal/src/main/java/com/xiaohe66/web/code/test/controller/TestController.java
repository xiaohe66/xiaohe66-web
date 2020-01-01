package com.xiaohe66.web.code.test.controller;

import com.xiaohe66.web.base.annotation.Get;
import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.base.data.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaohe
 * @time 2019.12.26 18:43
 */
@XhController("/test")
public class TestController {

    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    @Get
    public Result request(HttpServletRequest request) {

        Enumeration<String> headerNames = request.getHeaderNames();

        Map<String, String> map = new HashMap<>();

        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            String value = request.getHeader(name);
            map.put(name, value);
        }

        log.info("获取到的header : {}", map);

        return Result.ok(map);
    }
}
