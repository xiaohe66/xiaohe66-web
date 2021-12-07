package com.xiaohe66.web.gateway.http.love.dto;

import com.xiaohe66.web.gateway.http.PagingDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaohe
 * @since 2021.12.07 15:47
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MessageListDto extends PagingDto {

    private Long loverId;

}
