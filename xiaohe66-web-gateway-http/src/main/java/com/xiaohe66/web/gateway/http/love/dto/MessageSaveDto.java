package com.xiaohe66.web.gateway.http.love.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author xiaohe
 * @since 2021.12.07 15:51
 */
@Data
public class MessageSaveDto {

    @NotNull
    @Min(0)
    private Long loverId;

    @NotBlank
    @Size(max = 500, message = "长度不能超过500个字符")
    private String text;

}
