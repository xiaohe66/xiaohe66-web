package com.xiaohe66.web.domain.todo.entity;

import com.xiaohe66.web.domain.todo.value.TodoPoolId;
import com.xiaohe66.web.domain.todo.value.TodoPoolName;
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
public class TodoPool implements Entity<TodoPoolId> {

    private static final List<TodoPool> DEFAULT_POOL_LIST;

    private final TodoPoolId id;
    private final TodoPoolName name;

    static {

        String[] names = {"大事池", "随手池", "计划池", "准备池", "执行池", "验收池", "完成池"};

        TodoPool[] arr = new TodoPool[names.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = genTodoPool(i, names[i]);
        }

        DEFAULT_POOL_LIST = List.of(arr);
    }

    public static Collection<TodoPool> defaultPool() {
        return DEFAULT_POOL_LIST;
    }

    private static TodoPool genTodoPool(Integer idValue, String nameValue) {
        TodoPoolId id = new TodoPoolId(idValue);
        TodoPoolName name = new TodoPoolName(nameValue);
        return new TodoPool(id, name);
    }

}
