package com.xiaohe66.web.application.love.result;

import lombok.Data;

/**
 * @author xiaohe
 * @since 2021.12.01 17:42
 */
@Data
public class NewsResult {

    private Long id;
    private Long createId;
    private String createTime;
    private String text;
    private Long imageId;

}
