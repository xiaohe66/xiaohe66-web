package com.xiaohe66.web.infrastructure.mybatis.todo.convert;

import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.todo.agg.Todo;
import com.xiaohe66.web.domain.todo.value.TodoDesc;
import com.xiaohe66.web.domain.todo.value.TodoId;
import com.xiaohe66.web.domain.todo.value.TodoPoolId;
import com.xiaohe66.web.domain.todo.value.TodoSort;
import com.xiaohe66.web.domain.todo.value.TodoTitle;
import com.xiaohe66.web.infrastructure.mybatis.todo.model.TodoDo;
import com.xiaohe66.web.integration.DoConverter;
import org.mapstruct.Mapper;

/**
 * @author xiaohe
 * @since 2021.11.17 11:53
 */
@Mapper(componentModel = "spring")
public interface TodoDoConverter extends DoConverter<Todo, TodoDo> {

    default AccountId newCreateId(Long createId) {
        return ifPresent(createId, AccountId::new);
    }

    default TodoId newId(Long id) {
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
