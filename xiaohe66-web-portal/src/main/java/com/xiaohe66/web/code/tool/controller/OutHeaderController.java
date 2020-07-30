package com.xiaohe66.web.code.tool.controller;

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
 * @time 2020.07.30 23:03
 */
@XhController("/tool")
public class OutHeaderController {

    private static final Logger log = LoggerFactory.getLogger(OutHeaderController.class);

    @Get("/header")
    public Result header(HttpServletRequest request){
        Enumeration<String> headerNames = request.getHeaderNames();

        Map<String, String> map = new HashMap<>();

        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            String value = request.getHeader(name);
            map.put(name, value);
        }

        log.info("header : {}", map);

        return Result.ok(map);
    }

}
