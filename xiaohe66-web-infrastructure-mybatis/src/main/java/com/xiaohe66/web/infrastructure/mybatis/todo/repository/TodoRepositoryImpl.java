package com.xiaohe66.web.infrastructure.mybatis.todo.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.todo.agg.Todo;
import com.xiaohe66.web.domain.todo.repository.TodoRepository;
import com.xiaohe66.web.domain.todo.value.TodoId;
import com.xiaohe66.web.domain.todo.value.TodoPoolId;
import com.xiaohe66.web.infrastructure.mybatis.todo.convert.TodoDoConverter;
import com.xiaohe66.web.infrastructure.mybatis.todo.mapper.TodoMapper;
import com.xiaohe66.web.infrastructure.mybatis.todo.model.TodoDo;
import com.xiaohe66.web.integration.AbstractMybatisService;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiaohe
 * @since 2021.11.17 12:09
 */
@Repository
public class TodoRepositoryImpl
        extends AbstractMybatisService<TodoDoConverter, TodoMapper, TodoDo, Todo, TodoId>
        implements TodoRepository {

    @Override
    public List<Todo> listByPoolId(AccountId createId, TodoPoolId poolId) {

        LambdaQueryWrapper<TodoDo> queryWrapper = new LambdaQueryWrapper<TodoDo>()
                .eq(TodoDo::getPoolId, poolId.getValue())
                .eq(TodoDo::getCreateId, createId.getValue());

        List<TodoDo> list = list(queryWrapper);

        return list.stream()
                .map(dataConverter::toAgg)
                .collect(Collectors.toList());

    }
}
