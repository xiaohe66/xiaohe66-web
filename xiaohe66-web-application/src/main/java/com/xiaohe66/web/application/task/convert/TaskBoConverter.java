package com.xiaohe66.web.application.task.convert;

import com.xiaohe66.web.application.task.bo.TaskSaveBo;
import com.xiaohe66.web.application.task.result.TaskDetailResult;
import com.xiaohe66.web.application.task.result.TaskListResult;
import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.task.agg.Task;
import com.xiaohe66.web.domain.task.value.TaskChangeTime;
import com.xiaohe66.web.domain.task.value.TaskDesc;
import com.xiaohe66.web.domain.task.value.TaskId;
import com.xiaohe66.web.domain.task.value.TaskPoolId;
import com.xiaohe66.web.domain.task.value.TaskSort;
import com.xiaohe66.web.domain.task.value.TaskTitle;
import com.xiaohe66.web.integration.domain.DataConverter;
import org.mapstruct.Mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiaohe
 * @since 2021.11.17 14:45
 */
@Mapper(componentModel = "spring")
public abstract class TaskBoConverter implements DataConverter {

    public List<TaskListResult> convert(List<Task> originList) {

        Map<LocalDate, List<TaskListResult.Item>> map = new LinkedHashMap<>();

        for (Task task : originList) {

            LocalDate localDate = task.getChangeTime().getValue().toLocalDate();

            List<TaskListResult.Item> list = map.computeIfAbsent(localDate, k -> new ArrayList<>());
            list.add(toResult(task));
        }

        List<TaskListResult> resultList = new ArrayList<>(map.size());

        for (Map.Entry<LocalDate, List<TaskListResult.Item>> entry : map.entrySet()) {

            final TaskListResult item = new TaskListResult();
            item.setDate(entry.getKey());
            item.setList(entry.getValue());

            resultList.add(item);
        }

        return resultList;
    }

    public abstract Task toAgg(TaskSaveBo task, AccountId createId);

    public abstract TaskListResult.Item toResult(Task task);

    public abstract List<TaskListResult.Item> toResult(List<Task> task);

    public abstract TaskDetailResult toDetail(Task task);

    protected TaskId newTaskId(Long id) {
        return ifPresent(id, TaskId::new);
    }

    protected TaskPoolId newPoolId(Integer poolId) {
        return ifPresent(poolId, TaskPoolId::new);
    }

    protected TaskTitle newTitle(String title) {
        return ifPresent(title, TaskTitle::new);
    }

    protected TaskDesc newDesc(String desc) {
        return ifPresent(desc, TaskDesc::new);
    }

    protected TaskSort newTaskSort(Integer sort) {
        return ifPresent(sort, TaskSort::new);
    }

    protected LocalDateTime changeTime(TaskChangeTime changeTime) {
        return ifPresent(changeTime, TaskChangeTime::getValue);
    }

    protected TaskChangeTime changeTime(LocalDateTime changeTime) {
        return ifPresent(changeTime, TaskChangeTime::new);
    }
}
