package com.xiaohe66.web.application.task.convert;

import com.xiaohe66.web.application.task.bo.TaskSaveBo;
import com.xiaohe66.web.application.task.result.TaskDetailResult;
import com.xiaohe66.web.application.task.result.TaskListResult;
import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.task.agg.Task;
import com.xiaohe66.web.domain.task.value.TaskDesc;
import com.xiaohe66.web.domain.task.value.TaskId;
import com.xiaohe66.web.domain.task.value.TaskPoolId;
import com.xiaohe66.web.domain.task.value.TaskSort;
import com.xiaohe66.web.domain.task.value.TaskTitle;
import com.xiaohe66.web.integration.domain.DataConverter;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author xiaohe
 * @since 2021.11.17 14:45
 */
@Mapper(componentModel = "spring")
public interface TaskBoConverter extends DataConverter {

    Task toAgg(TaskSaveBo task, AccountId createId);

    TaskListResult toResult(Task task);

    List<TaskListResult> toResult(List<Task> task);

    TaskDetailResult toDetail(Task task);

    default TaskId newTaskId(Long id) {
        return ifPresent(id, TaskId::new);
    }

    default TaskPoolId newPoolId(Integer poolId) {
        return ifPresent(poolId, TaskPoolId::new);
    }

    default TaskTitle newTitle(String title) {
        return ifPresent(title, TaskTitle::new);
    }

    default TaskDesc newDesc(String desc) {
        return ifPresent(desc, TaskDesc::new);
    }

    default TaskSort newTaskSort(Integer sort) {
        return ifPresent(sort, TaskSort::new);
    }

}
