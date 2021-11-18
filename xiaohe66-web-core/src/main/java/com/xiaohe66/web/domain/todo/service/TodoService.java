package com.xiaohe66.web.domain.todo.service;

import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.sys.sec.service.SecurityService;
import com.xiaohe66.web.domain.todo.agg.Todo;
import com.xiaohe66.web.domain.todo.repository.TodoRepository;
import com.xiaohe66.web.domain.todo.value.TodoId;
import com.xiaohe66.web.domain.todo.value.TodoPoolId;
import com.xiaohe66.web.domain.todo.value.TodoSort;
import com.xiaohe66.web.integration.ex.BusinessException;
import com.xiaohe66.web.integration.ex.ErrorCodeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiaohe
 * @since 2021.11.17 14:17
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    private final SecurityService securityService;

    public void saveTodo(Todo todo) {

        todoRepository.save(todo);
    }

    public void removeById(TodoId id) {

        Todo todo = todoRepository.getById(id);

        if (todo == null) {
            throw new BusinessException(ErrorCodeEnum.NOT_FOUND);
        }

        securityService.checkCreatorPermission(todo.getCreateId());
        todoRepository.removeById(id);
    }

    public void changePool(TodoId todoId, TodoPoolId poolId) {

        Todo todo = todoRepository.getById(todoId);
        if (todo == null) {
            throw new BusinessException(ErrorCodeEnum.NOT_FOUND);
        }
        todo.changePool(poolId);

        todoRepository.save(todo);
    }

    public void sort(TodoPoolId poolId, List<TodoId> todoIdList) {

        AccountId accountId = securityService.getCurrentAccountId();

        // 默认排序是0, 自定义排序会从1开始,这样新建的就会放在前面
        int sort = 1;
        for (TodoId todoId : todoIdList) {
            Todo todo = todoRepository.getById(todoId);
            // 没有权限
            // 池不同
            if (!accountId.equals(todo.getCreateId()) ||
                    !poolId.equals(todo.getPoolId())) {
                continue;
            }

            todo.changeSort(TodoSort.valueOf(sort));
            todoRepository.save(todo);

            sort++;
        }
    }

}
