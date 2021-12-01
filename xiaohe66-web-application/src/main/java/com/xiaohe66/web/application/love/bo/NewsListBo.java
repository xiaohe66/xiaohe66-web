package com.xiaohe66.web.application.love.bo;

import com.xiaohe66.web.application.PagingBo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaohe
 * @since 2021.12.01 17:43
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class NewsListBo extends PagingBo {

    private Long loverId;

}
