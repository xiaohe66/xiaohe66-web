package com.xiaohe66.web.base.base;


import com.google.gson.Gson;
import com.xiaohe66.web.base.data.CodeEnum;
import com.xiaohe66.web.base.exception.XhWebException;
import com.xiaohe66.web.base.holder.OkHttpClientHolder;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author xiaohe
 * @time 2019.12.10 15:41
 */
@Slf4j
public abstract class BaseRequester<I extends BaseRequest, O extends BaseResponse> {

    private static final Gson gson = new Gson();

    protected final OkHttpClient client;

    protected final Class<O> responseCls;

    @SuppressWarnings("unchecked")
    public BaseRequester() {
        client = OkHttpClientHolder.get();

        Type[] type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments();
        responseCls = ((Class<O>) type[1]);
    }

    public void call(I request, BaseCallback<O> callback) {
        Request okRequest = buildRequest(request);
        client.newCall(okRequest).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                log.warn("请求失败", e);
                callback.onResponse(null);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                O o = parseResponse(response);
                callback.onResponse(o);
            }
        });
    }

    public O call(I request) {
        Request okRequest = buildRequest(request);
        try {
            Response response = client.newCall(okRequest).execute();
            return parseResponse(response);
        } catch (IOException e) {
            throw new XhWebException(CodeEnum.IO_EXCEPTION, e);
        }
    }

    protected abstract Request buildRequest(I request);

    protected O parseResponse(Response response) {
        if (!response.isSuccessful()) {
            log.info("请求失败, code : {}", response.code());
            return null;
        }

        ResponseBody body = response.body();
        if (body == null) {
            log.info("response body 为空");
            return null;
        }
        String bodyString;
        try {
            bodyString = body.string();
            log.debug("bodyString : {}", bodyString);
        } catch (IOException e) {
            log.warn("读取body时发生IO异常", e);
            return null;
        }
        return convert(bodyString);
    }

    protected O convert(String body) {
        return gson.fromJson(body, responseCls);
    }

}
