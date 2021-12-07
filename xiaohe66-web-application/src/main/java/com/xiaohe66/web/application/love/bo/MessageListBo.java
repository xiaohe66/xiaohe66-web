package com.xiaohe66.web.application.love.bo;

import com.xiaohe66.web.application.PagingBo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaohe
 * @since 2021.12.07 11:53
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MessageListBo extends PagingBo {

    private Long loverId;

}
