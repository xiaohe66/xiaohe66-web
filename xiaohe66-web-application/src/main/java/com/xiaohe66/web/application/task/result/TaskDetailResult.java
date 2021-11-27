package com.xiaohe66.web.application.task.result;

import lombok.Data;

/**
 * @author xiaohe
 * @since 2021.11.18 15:08
 */
@Data
public class TaskDetailResult {

    private Long id;
    private Integer poolId;
    private String name;
    private String remark;
    private Integer sort;

}
