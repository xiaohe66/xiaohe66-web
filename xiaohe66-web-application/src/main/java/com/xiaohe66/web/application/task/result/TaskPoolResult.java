package com.xiaohe66.web.application.task.result;

import lombok.Data;

import java.util.List;

/**
 * @author xiaohe
 * @since 2021.12.28 21:00
 */
@Data
public class TaskPoolResult {

    private Integer qty;
    private List<TaskDateResult> dateList;

}
