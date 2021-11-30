package com.xiaohe66.web.gateway.http.love.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author xiaohe
 * @since 2021.11.30 14:27
 */
@Data
public class DailySaveDto {

    @NotBlank
    private String desc;

    @NotNull
    @Min(0)
    private Integer mood;
}
