package com.xiaohe66.web.application.todo.result;

import lombok.Data;

/**
 * @author xiaohe
 * @since 2021.11.18 15:08
 */
@Data
public class TodoDetailResult {

    private Long id;
    private Integer poolId;
    private String title;
    private String desc;
    private String summary;
    private Integer sort;

}
