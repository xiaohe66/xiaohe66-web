package com.xiaohe66.web.infrastructure.mybatis.task.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.task.agg.Task;
import com.xiaohe66.web.domain.task.repository.TaskRepository;
import com.xiaohe66.web.domain.task.value.TaskId;
import com.xiaohe66.web.domain.task.value.TaskPoolId;
import com.xiaohe66.web.infrastructure.mybatis.task.convert.TaskDoConverter;
import com.xiaohe66.web.infrastructure.mybatis.task.mapper.TaskMapper;
import com.xiaohe66.web.infrastructure.mybatis.task.model.TaskDo;
import com.xiaohe66.web.integration.AbstractMybatisService;
import com.xiaohe66.web.integration.domain.Paging;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiaohe
 * @since 2021.11.17 12:09
 */
@Repository
public class TaskRepositoryImpl
        extends AbstractMybatisService<TaskDoConverter, TaskMapper, TaskDo, Task, TaskId>
        implements TaskRepository {

    @Override
    public List<Task> listByPoolId(AccountId createId, TaskPoolId poolId, Paging paging) {

        LambdaQueryWrapper<TaskDo> queryWrapper = new LambdaQueryWrapper<TaskDo>()
                .eq(TaskDo::getPoolId, poolId.getValue())
                .eq(TaskDo::getCreateId, createId.getValue())
                // .orderByAsc(TaskDo::getSort)
                .orderByDesc(TaskDo::getChangeTime)
                .last(paging.toLimit());

        List<TaskDo> list = list(queryWrapper);

        return dataConverter.toAgg(list);
    }
}
