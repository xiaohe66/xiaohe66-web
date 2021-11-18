package com.xiaohe66.web.domain.todo.agg;

import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.todo.value.TodoDesc;
import com.xiaohe66.web.domain.todo.value.TodoId;
import com.xiaohe66.web.domain.todo.value.TodoPoolId;
import com.xiaohe66.web.domain.todo.value.TodoSort;
import com.xiaohe66.web.domain.todo.value.TodoSummary;
import com.xiaohe66.web.domain.todo.value.TodoTitle;
import com.xiaohe66.web.integration.domain.Aggregate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.Objects;

/**
 * @author xiaohe
 * @since 2021.11.12 18:02
 */
@Builder
@Getter
@ToString
@AllArgsConstructor
public class Todo implements Aggregate<Todo, TodoId> {

    @NonNull
    private final TodoId id;

    @NonNull
    private final AccountId createId;

    @NonNull
    private TodoPoolId poolId;

    @NonNull
    private TodoTitle title;

    private TodoDesc desc;
    private TodoSummary summary;
    private TodoSort sort;

    public void changePool(TodoPoolId poolId) {
        this.poolId = poolId;
        this.sort = TodoSort.valueOf(0);
    }

    public void changeSort(TodoSort sort) {
        this.sort = sort;
    }

    @Override
    public boolean hasDiffRoot(Todo other) {
        return !(Objects.equals(poolId, other.poolId) &&
                Objects.equals(title, other.title) &&
                Objects.equals(desc, other.desc) &&
                Objects.equals(summary, other.summary) &&
                Objects.equals(sort, other.sort));
    }

    @Override
    public TodoId getId() {
        return id;
    }
}
