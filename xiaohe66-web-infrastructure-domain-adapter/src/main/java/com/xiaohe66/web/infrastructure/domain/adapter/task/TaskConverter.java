package com.xiaohe66.web.infrastructure.domain.adapter.task;

import com.xiaohe66.web.domain.task.value.TaskChangeTime;
import com.xiaohe66.web.domain.task.value.TaskDesc;
import com.xiaohe66.web.domain.task.value.TaskId;
import com.xiaohe66.web.domain.task.value.TaskPoolId;
import com.xiaohe66.web.domain.task.value.TaskSort;
import com.xiaohe66.web.domain.task.value.TaskTitle;
import com.xiaohe66.web.infrastructure.domain.adapter.DataConverter;

import java.time.LocalDateTime;

/**
 * @author xiaohe
 * @since 2022.03.30 11:49
 */
public interface TaskConverter extends DataConverter {

    default TaskId newId(Long id) {
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

    default LocalDateTime changeTime(TaskChangeTime changeTime) {
        return ifPresent(changeTime, TaskChangeTime::getValue);
    }

    default TaskChangeTime changeTime(LocalDateTime changeTime) {
        return ifPresent(changeTime, TaskChangeTime::new);
    }

}
