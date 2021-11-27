package com.xiaohe66.web.application.task.bo;

import lombok.Data;

import java.util.List;

/**
 * @author xiaohe
 * @since 2021.11.17 17:39
 */
@Data
public class TaskSortBo {

    private Integer poolId;
    private List<Long> taskIds;

}
