package com.xiaohe66.web.application.todo.convert;

import com.xiaohe66.web.application.todo.bo.TodoSaveBo;
import com.xiaohe66.web.application.todo.result.TodoDetailResult;
import com.xiaohe66.web.application.todo.result.TodoListResult;
import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.todo.agg.Todo;
import com.xiaohe66.web.domain.todo.value.TodoDesc;
import com.xiaohe66.web.domain.todo.value.TodoId;
import com.xiaohe66.web.domain.todo.value.TodoPoolId;
import com.xiaohe66.web.domain.todo.value.TodoSort;
import com.xiaohe66.web.domain.todo.value.TodoTitle;
import com.xiaohe66.web.integration.domain.DataConverter;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author xiaohe
 * @since 2021.11.17 14:45
 */
@Mapper(componentModel = "spring")
public interface TodoBoConverter extends DataConverter {

    Todo toAgg(TodoSaveBo todo, AccountId createId);

    TodoListResult toResult(Todo todo);

    List<TodoListResult> toResult(List<Todo> todo);

    TodoDetailResult toDetail(Todo todo);

    default TodoId newTodoId(Long id) {
        return ifPresent(id, TodoId::new);
    }

    default TodoPoolId newPoolId(Integer poolId) {
        return ifPresent(poolId, TodoPoolId::new);
    }

    default TodoTitle newTitle(String title) {
        return ifPresent(title, TodoTitle::new);
    }

    default TodoDesc newDesc(String desc) {
        return ifPresent(desc, TodoDesc::new);
    }

    default TodoSort newTodoSort(Integer sort) {
        return ifPresent(sort, TodoSort::new);
    }

}
