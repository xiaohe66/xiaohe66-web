package com.xiaohe66.web.domain.task.repository;

import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.task.agg.Task;
import com.xiaohe66.web.domain.task.value.TaskId;
import com.xiaohe66.web.domain.task.value.TaskPoolId;
import com.xiaohe66.web.integration.domain.Repository;

import java.util.List;

/**
 * @author xiaohe
 * @since 2021.11.15 11:09
 */
public interface TaskRepository extends Repository<Task, TaskId> {

    List<Task> listByPoolId(AccountId createId, TaskPoolId poolId, Long before, Long size);

}
