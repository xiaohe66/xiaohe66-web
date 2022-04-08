package com.xiaohe66.web.gateway.http.love.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * @author xiaohe
 * @since 2022.03.29 16:18
 */
@Data
public class WishSaveDto {

    private Long id;

    @Size(min = 1, max = 3000)
    private String title;

    @Size(max = 3000)
    private String desc;

    @NotNull
    private Boolean finished;

    private LocalDate finishDate;

    @Size(max = 3000)
    private String remark;
}
