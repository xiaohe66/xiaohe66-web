package com.xiaohe66.web.gateway.http.love.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author xiaohe
 * @since 2021.12.19 13:43
 */
@Data
public class NewsBannerListDto {

    @NotNull
    @Min(0)
    private Long loverId;
    
}
