package com.xiaohe66.web.application.love.bo;

import com.xiaohe66.web.application.PagingBo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaohe
 * @since 2022.03.30 10:36
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WishListBo extends PagingBo {

    private Boolean finished;

}