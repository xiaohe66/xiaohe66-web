package com.xiaohe66.web.base.base;

/**
 * @author xiaohe
 * @time 2019.12.10 15:42
 */
public interface BaseCallback<O extends BaseResponse> {

    void onResponse(O response);

    void onException(Exception e);

}
