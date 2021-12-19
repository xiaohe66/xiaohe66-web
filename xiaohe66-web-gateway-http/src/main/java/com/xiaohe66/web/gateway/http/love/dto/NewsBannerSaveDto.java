package com.xiaohe66.web.gateway.http.love.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author xiaohe
 * @since 2021.12.19 13:31
 */
@Data
public class NewsBannerSaveDto {

    @Size(min = 1, max = 5)
    @NotNull
    private List<Long> imageIds;
}
