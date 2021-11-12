package com.xiaohe66.web.gateway.http;

/**
 * @author xiaohe
 * @since 2021.11.12 17:05
 */
public interface DtoConverter<R, B> {

    B toBo(R request);

}
