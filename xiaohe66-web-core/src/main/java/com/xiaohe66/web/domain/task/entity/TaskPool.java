package com.xiaohe66.web.domain.task.entity;

import com.xiaohe66.web.domain.task.value.TaskPoolId;
import com.xiaohe66.web.domain.task.value.TaskPoolName;
import com.xiaohe66.web.integration.domain.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Collection;
import java.util.List;

/**
 * @author xiaohe
 * @since 2021.11.17 15:44
 */
@Builder
@Getter
@ToString
@AllArgsConstructor
public class TaskPool implements Entity<TaskPoolId> {

    private static final List<TaskPool> DEFAULT_POOL_LIST;

    private final TaskPoolId id;
    private final TaskPoolName name;

    static {

        String[] names = {"大事池", "随手池", "计划池", "准备池", "执行池", "验收池", "完成池"};

        TaskPool[] arr = new TaskPool[names.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = genTaskPool(i, names[i]);
        }

        DEFAULT_POOL_LIST = List.of(arr);
    }

    public static Collection<TaskPool> defaultPool() {
        return DEFAULT_POOL_LIST;
    }

    private static TaskPool genTaskPool(Integer idValue, String nameValue) {
        TaskPoolId id = new TaskPoolId(idValue);
        TaskPoolName name = new TaskPoolName(nameValue);
        return new TaskPool(id, name);
    }

}
