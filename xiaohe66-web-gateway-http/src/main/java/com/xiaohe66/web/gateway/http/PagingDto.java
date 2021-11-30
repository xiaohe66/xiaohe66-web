package com.xiaohe66.web.gateway.http;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author xiaohe
 * @since 2021.11.30 14:36
 */
@Data
public class PagingDto {

    @Min(0)
    private Long before;

    @Min(5)
    @Max(50)
    private Integer size;
}
