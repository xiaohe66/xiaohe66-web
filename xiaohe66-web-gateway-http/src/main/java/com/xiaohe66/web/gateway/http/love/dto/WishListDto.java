package com.xiaohe66.web.gateway.http.love.dto;

import com.xiaohe66.web.gateway.http.PagingDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaohe
 * @since 2022.03.29 16:16
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WishListDto extends PagingDto {

    private Boolean finished;
}
