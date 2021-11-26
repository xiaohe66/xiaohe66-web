package com.xiaohe66.web.application.todo;

import com.xiaohe66.common.dto.R;
import com.xiaohe66.common.util.IdWorker;
import com.xiaohe66.web.application.aop.annotation.NeedLogin;
import com.xiaohe66.web.application.todo.bo.TodoChangePoolBo;
import com.xiaohe66.web.application.todo.bo.TodoListBo;
import com.xiaohe66.web.application.todo.bo.TodoSaveBo;
import com.xiaohe66.web.application.todo.bo.TodoSortBo;
import com.xiaohe66.web.application.todo.convert.TodoBoConverter;
import com.xiaohe66.web.application.todo.result.TodoDetailResult;
import com.xiaohe66.web.application.todo.result.TodoListResult;
import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.sys.sec.service.SecurityService;
import com.xiaohe66.web.domain.todo.agg.Todo;
import com.xiaohe66.web.domain.todo.entity.TodoPool;
import com.xiaohe66.web.domain.todo.repository.TodoRepository;
import com.xiaohe66.web.domain.todo.service.TodoService;
import com.xiaohe66.web.domain.todo.value.TodoId;
import com.xiaohe66.web.domain.todo.value.TodoPoolId;
import com.xiaohe66.web.integration.ex.BusinessException;
import com.xiaohe66.web.integration.ex.ErrorCodeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiaohe
 * @since 2021.11.17 14:38
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TodoAppService {

    private final TodoBoConverter converter;

    private final TodoService todoService;
    private final TodoRepository todoRepository;
    private final SecurityService securityService;

    @NeedLogin
    public R<Long> save(TodoSaveBo saveBo) {

        AccountId accountId = securityService.getCurrentAccountId();

        if (saveBo.getId() == null) {
            saveBo.setId(IdWorker.genId());
        }
        Todo todo = converter.toAgg(saveBo, accountId);
        todoService.saveTodo(todo);

        return R.ok(saveBo.getId());
    }

    @NeedLogin
    public R<Void> remove(Long id) {

        TodoId todoId = new TodoId(id);
        todoService.removeById(todoId);
        return R.ok();
    }

    @NeedLogin
    public R<Void> changeStatus(TodoChangePoolBo poolBo) {

        TodoId todoId = new TodoId(poolBo.getTodoId());
        TodoPoolId poolId = new TodoPoolId(poolBo.getPoolId());

        todoService.changePool(todoId, poolId);

        return R.ok();
    }

    @NeedLogin
    public R<Void> sort(TodoSortBo sortBo) {

        TodoPoolId poolId = new TodoPoolId(sortBo.getPoolId());

        List<TodoId> todoIdList = sortBo.getTodoIds().stream()
                .map(TodoId::new)
                .collect(Collectors.toList());

        todoService.sort(poolId, todoIdList);

        return R.ok();
    }

    /**
     * 用于页面的首次获取
     */
    @NeedLogin
    public R<List<List<TodoListResult>>> queryLists() {

        AccountId currentAccountId = securityService.getCurrentAccountId();

        List<List<TodoListResult>> list = TodoPool.defaultPool().stream()
                .map(pool -> {

                    List<Todo> todos = todoRepository.listByPoolId(currentAccountId, pool.getId(), 0L, 10L);
                    return converter.toResult(todos);

                }).collect(Collectors.toList());

        return R.ok(list);
    }

    @NeedLogin
    public R<List<TodoListResult>> queryList(TodoListBo bo) {

        AccountId currentAccountId = securityService.getCurrentAccountId();

        TodoPoolId todoPoolId = new TodoPoolId(bo.getPoolId());

        List<Todo> todos = todoRepository.listByPoolId(currentAccountId, todoPoolId, bo.getBefore(), bo.getSize());
        List<TodoListResult> ret = converter.toResult(todos);

        return R.ok(ret);
    }

    @NeedLogin
    public R<TodoDetailResult> queryDetail(Long id) {

        Todo todo = todoRepository.getById(new TodoId(id));
        if (todo == null) {
            throw new BusinessException(ErrorCodeEnum.NOT_FOUND);
        }

        TodoDetailResult result = converter.toDetail(todo);

        return R.ok(result);
    }

}
