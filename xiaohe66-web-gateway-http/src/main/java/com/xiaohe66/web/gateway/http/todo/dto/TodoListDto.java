package com.xiaohe66.web.gateway.http.todo.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author xiaohe
 * @since 2021.11.26 16:03
 */
@Data
public class TodoListDto {

    @Min(0)
    private Integer before;

    @Min(5)
    @Max(50)
    private Long size;

    @Min(0)
    @Max(6)
    private Integer poolId;

}
