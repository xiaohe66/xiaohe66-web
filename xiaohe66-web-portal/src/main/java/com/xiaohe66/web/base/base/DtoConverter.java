package com.xiaohe66.web.base.base;

/**
 * @author xiaohe
 * @time 2019.10.24 23:34
 */
public interface DtoConverter<P extends BasePo,D extends BaseDto> {

    void convertDto(D dto,P po);

}
