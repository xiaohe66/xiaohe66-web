package com.xiaohe66.web.base.base;

import com.xiaohe66.common.net.OkHttpClientHolder;
import com.xiaohe66.common.net.req.AbstractHttpRequester;
import com.xiaohe66.web.config.WxConfig;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Type;

/**
 * @author xiaohe
 * @time 2021.01.06 17:06
 */
public abstract class WxHttpRequester<P, R> extends AbstractHttpRequester<P, R> {

    @Autowired
    private WxConfig wxConfig;

    public WxHttpRequester(String queryUrl, Type beanType) {
        super("https://api.weixin.qq.com", queryUrl, OkHttpClientHolder.get(), beanType);
    }

}
