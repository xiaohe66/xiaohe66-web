package com.xiaohe66.web.gateway.http.love.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author xiaohe
 * @since 2021.11.30 14:27
 */
@Data
public class DailySaveDto {

    /**
     * 最多 500个中文字
     */
    @Size(min = 1, max = 3000)
    private String desc;

    @NotNull
    @Min(0)
    private Integer mood;
}
