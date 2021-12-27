package com.xiaohe66.web.domain.task.agg;

import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.task.value.TaskChangeTime;
import com.xiaohe66.web.domain.task.value.TaskDesc;
import com.xiaohe66.web.domain.task.value.TaskId;
import com.xiaohe66.web.domain.task.value.TaskPoolId;
import com.xiaohe66.web.domain.task.value.TaskSort;
import com.xiaohe66.web.domain.task.value.TaskTitle;
import com.xiaohe66.web.integration.domain.Aggregate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author xiaohe
 * @since 2021.11.12 18:02
 */
@Builder
@Getter
@ToString
@AllArgsConstructor
public class Task implements Aggregate<Task, TaskId> {

    @NonNull
    private final TaskId id;

    @NonNull
    private final AccountId createId;

    @NonNull
    private TaskPoolId poolId;

    @Setter
    private TaskChangeTime changeTime;

    @NonNull
    private TaskTitle name;

    private TaskDesc remark;
    private TaskSort sort;

    public void changePool(TaskPoolId poolId) {
        this.poolId = poolId;
        this.sort = TaskSort.valueOf(0);
        this.changeTime = new TaskChangeTime(LocalDateTime.now());
    }

    public void changeSort(TaskSort sort) {
        this.sort = sort;
    }

    @Override
    public boolean hasSameRootAttribute(Task other) {
        return other != null &&
                Objects.equals(poolId, other.poolId) &&
                Objects.equals(changeTime, other.changeTime) &&
                Objects.equals(name, other.name) &&
                Objects.equals(remark, other.remark) &&
                Objects.equals(sort, other.sort);
    }
}
