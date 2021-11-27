package com.xiaohe66.web.gateway.http.task.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author xiaohe
 * @since 2021.11.17 17:19
 */
@Data
public class TaskChangePoolDto {

    @NotNull
    @Min(1)
    private Long taskId;

    @NotNull
    @Min(1)
    @Max(7)
    private Integer poolId;

}
