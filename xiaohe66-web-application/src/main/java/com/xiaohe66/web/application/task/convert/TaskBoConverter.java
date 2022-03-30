package com.xiaohe66.web.application.task.convert;

import com.xiaohe66.web.application.task.bo.TaskSaveBo;
import com.xiaohe66.web.application.task.result.TaskDateResult;
import com.xiaohe66.web.application.task.result.TaskDetailResult;
import com.xiaohe66.web.application.task.result.TaskPoolResult;
import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.task.agg.Task;
import com.xiaohe66.web.infrastructure.domain.adapter.task.TaskConverter;
import org.mapstruct.Mapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiaohe
 * @since 2021.11.17 14:45
 */
@Mapper
public abstract class TaskBoConverter implements TaskConverter {

    public TaskPoolResult convert(List<Task> originList) {

        Map<LocalDate, List<TaskDateResult.Item>> map = new LinkedHashMap<>();

        for (Task task : originList) {

            LocalDate localDate = task.getChangeTime().getValue().toLocalDate();

            List<TaskDateResult.Item> list = map.computeIfAbsent(localDate, k -> new ArrayList<>());
            list.add(toResult(task));
        }

        List<TaskDateResult> resultList = new ArrayList<>(map.size());

        for (Map.Entry<LocalDate, List<TaskDateResult.Item>> entry : map.entrySet()) {

            final TaskDateResult result = new TaskDateResult();
            result.setDate(entry.getKey());
            result.setList(entry.getValue());

            resultList.add(result);
        }

        TaskPoolResult poolResult = new TaskPoolResult();

        poolResult.setQty(originList.size());
        poolResult.setDateList(resultList);

        return poolResult;
    }

    public abstract Task toAgg(TaskSaveBo task, AccountId createId);

    public abstract TaskDateResult.Item toResult(Task task);

    public abstract List<TaskDateResult.Item> toResult(List<Task> task);

    public abstract TaskDetailResult toDetail(Task task);

}
