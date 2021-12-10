package com.xiaohe66.web.gateway.http.love.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author xiaohe
 * @since 2021.12.01 17:55
 */
@Data
public class NewsSaveDto {

    @NotBlank
    private String text;

    @NotNull
    @Min(0)
    private Long imageId;

}
