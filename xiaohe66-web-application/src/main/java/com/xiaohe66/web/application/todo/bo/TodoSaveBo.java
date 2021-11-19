package com.xiaohe66.web.application.todo.bo;

import lombok.Data;

/**
 * @author xiaohe
 * @since 2021.11.17 14:44
 */
@Data
public class TodoSaveBo {

    private Long id;
    private Integer poolId;
    private String title;
    private String desc;
    private String summary;
    private Integer sort;
}
