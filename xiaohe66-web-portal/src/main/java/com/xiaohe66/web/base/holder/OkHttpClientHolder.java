package com.xiaohe66.web.base.holder;

import okhttp3.OkHttpClient;

/**
 * @author xiaohe
 * @time 2019.12.10 15:55
 */
public class OkHttpClientHolder {

    // todo : 参数调整
    private static OkHttpClient client = new OkHttpClient();

    private OkHttpClientHolder(){}

    public static OkHttpClient get(){
        return client;
    }
}
