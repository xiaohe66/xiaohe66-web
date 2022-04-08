package com.xiaohe66.web.application.love.result;

import lombok.Data;

/**
 * @author xiaohe
 * @since 2022.03.29 18:17
 */
@Data
public class WishResult {

    private Long id;
    private Long loverId;
    private String title;
    private String desc;
    private Boolean finished;
    private String finishDate;
    private String remark;
}
