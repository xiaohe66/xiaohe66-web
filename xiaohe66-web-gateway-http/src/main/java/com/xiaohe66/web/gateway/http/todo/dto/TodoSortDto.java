package com.xiaohe66.web.gateway.http.todo.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author xiaohe
 * @since 2021.11.17 17:42
 */
@Data
public class TodoSortDto {

    @NotNull
    @Min(1)
    @Max(7)
    private Integer poolId;

    @NotNull
    @Size(min = 2)
    private List<Long> todoIds;
}
