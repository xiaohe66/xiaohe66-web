package com.xiaohe66.web.base.base;

import com.xiaohe66.common.net.OkHttpClientHolder;
import com.xiaohe66.common.net.req.AbstractHttpRequester;
import com.xiaohe66.common.util.JsonUtils;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import java.lang.reflect.Type;

/**
 * @author xiaohe
 * @time 2021.01.06 17:06
 */
public abstract class WxHttpRequester<P, R> extends AbstractHttpRequester<P, R> {

    protected static final MediaType jsonMediaType = MediaType.parse("application/json; charset=utf-8");

    public WxHttpRequester(String queryUrl, Type beanType) {
        super("https://api.weixin.qq.com", queryUrl, OkHttpClientHolder.get(), beanType);
    }

    protected final RequestBody createJsonBody(P request) {

        return RequestBody.create(JsonUtils.toString(request), jsonMediaType);
    }

}
