package com.xiaohe66.web.domain.todo.repository;

import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.todo.agg.Todo;
import com.xiaohe66.web.domain.todo.value.TodoId;
import com.xiaohe66.web.domain.todo.value.TodoPoolId;
import com.xiaohe66.web.integration.domain.Repository;

import java.util.List;

/**
 * @author xiaohe
 * @since 2021.11.15 11:09
 */
public interface TodoRepository extends Repository<Todo, TodoId> {

    List<Todo> listByPoolId(AccountId createId, TodoPoolId poolId, Long before, Long size);

}
