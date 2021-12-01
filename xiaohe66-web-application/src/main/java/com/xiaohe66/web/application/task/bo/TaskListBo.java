package com.xiaohe66.web.application.task.bo;

import com.xiaohe66.web.application.PagingBo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaohe
 * @since 2021.11.26 16:18
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TaskListBo extends PagingBo {

    private Integer poolId;
}
