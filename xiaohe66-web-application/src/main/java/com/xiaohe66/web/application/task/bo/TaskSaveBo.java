package com.xiaohe66.web.application.task.bo;

import lombok.Data;

/**
 * @author xiaohe
 * @since 2021.11.17 14:44
 */
@Data
public class TaskSaveBo {

    private Long id;
    private Integer poolId;
    private String name;
    private String remark;
    private Integer sort;
}
