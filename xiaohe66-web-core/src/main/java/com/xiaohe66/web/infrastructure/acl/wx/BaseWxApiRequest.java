package com.xiaohe66.web.infrastructure.acl.wx;


import com.xiaohe66.common.api.IApiModel;
import com.xiaohe66.common.api.okhttp.BaseOkHttpApiRequest;
import com.xiaohe66.common.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @author xiaohe
 * @time 2019.12.10 15:41
 */
@Slf4j
public abstract class BaseWxApiRequest<E extends BaseWxApiResponse> extends BaseOkHttpApiRequest<E> {

    protected static final MediaType jsonMediaType = MediaType.parse("application/json; charset=utf-8");

    public BaseWxApiRequest(Method method) {
        super(method);
    }

    @Override
    public RequestBody buildRequestBody() {

        IApiModel model = getModel();

        String body = JsonUtils.toString(model);

        return RequestBody.create(jsonMediaType, body);
    }
}
