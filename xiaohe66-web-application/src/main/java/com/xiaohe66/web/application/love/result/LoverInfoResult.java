package com.xiaohe66.web.application.love.result;

import lombok.Data;

/**
 * @author xiaohe
 * @since  2020.01.06 16:43
 */
@Data
public class LoverInfoResult {

    private Long id;
    private Long createId;
    private Long loveId;
    private String createTime;
    private Integer status;

    private String loverNickname;

}
