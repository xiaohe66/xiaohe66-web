package com.xiaohe66.web.gateway.http.love.dto;

import com.xiaohe66.web.gateway.http.PagingDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author xiaohe
 * @since 2021.12.01 17:58
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class NewsListDto extends PagingDto {

    @NotNull
    @Min(0)
    private Long loverId;
}
