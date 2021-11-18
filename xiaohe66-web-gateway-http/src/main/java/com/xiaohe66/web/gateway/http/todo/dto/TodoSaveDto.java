package com.xiaohe66.web.gateway.http.todo.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author xiaohe
 * @since 2021.11.17 14:21
 */
@Data
public class TodoSaveDto {

    @Min(1)
    private Long id;

    @NotNull
    private Integer poolId;

    @NotBlank
    @Size(max = 16)
    private String title;

    private String desc;
    private String summary;
    private Integer sort;

}
