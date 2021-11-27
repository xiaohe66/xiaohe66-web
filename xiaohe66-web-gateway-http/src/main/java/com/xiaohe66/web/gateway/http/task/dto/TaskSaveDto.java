package com.xiaohe66.web.gateway.http.task.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author xiaohe
 * @since 2021.11.17 14:21
 */
@Data
public class TaskSaveDto {

    @Min(1)
    private Long id;

    @NotNull
    @Min(0)
    @Max(6)
    private Integer poolId;

    /**
     * 最多 20个中文字符
     */
    @NotBlank
    @Size(max = 120)
    private String name;

    /**
     * 最多 100个中文字条
     */
    @Size(max = 600)
    private String remark;

    @Min(0)
    private Integer sort;

}
