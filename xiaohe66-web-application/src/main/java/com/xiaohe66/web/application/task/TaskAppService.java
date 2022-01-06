package com.xiaohe66.web.application.task;

import com.xiaohe66.common.dto.R;
import com.xiaohe66.common.util.IdWorker;
import com.xiaohe66.common.util.ex.BusinessException;
import com.xiaohe66.common.util.ex.ErrorCodeEnum;
import com.xiaohe66.web.application.aop.annotation.NeedLogin;
import com.xiaohe66.web.application.task.bo.TaskChangePoolBo;
import com.xiaohe66.web.application.task.bo.TaskListBo;
import com.xiaohe66.web.application.task.bo.TaskSaveBo;
import com.xiaohe66.web.application.task.bo.TaskSortBo;
import com.xiaohe66.web.application.task.convert.TaskBoConverter;
import com.xiaohe66.web.application.task.result.TaskDetailResult;
import com.xiaohe66.web.application.task.result.TaskPoolResult;
import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.sys.sec.service.SecurityService;
import com.xiaohe66.web.domain.task.agg.Task;
import com.xiaohe66.web.domain.task.entity.TaskPool;
import com.xiaohe66.web.domain.task.repository.TaskRepository;
import com.xiaohe66.web.domain.task.service.TaskService;
import com.xiaohe66.web.domain.task.value.TaskId;
import com.xiaohe66.web.domain.task.value.TaskPoolId;
import com.xiaohe66.web.integration.domain.Paging;
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
public class TaskAppService {

    private final TaskBoConverter converter;

    private final TaskService taskService;
    private final TaskRepository taskRepository;
    private final SecurityService securityService;

    @NeedLogin
    public R<Long> save(TaskSaveBo saveBo) {

        AccountId accountId = securityService.getCurrentAccountId();

        if (saveBo.getId() == null) {
            saveBo.setId(IdWorker.genId());
        }
        Task task = converter.toAgg(saveBo, accountId);
        taskService.saveTask(task);

        return R.ok(saveBo.getId());
    }

    @NeedLogin
    public R<Void> remove(Long id) {

        TaskId taskId = new TaskId(id);
        taskService.removeById(taskId);
        return R.ok();
    }

    @NeedLogin
    public R<Void> changeStatus(TaskChangePoolBo poolBo) {

        TaskId taskId = new TaskId(poolBo.getTaskId());
        TaskPoolId poolId = new TaskPoolId(poolBo.getPoolId());

        taskService.changePool(taskId, poolId);

        return R.ok();
    }

    @NeedLogin
    public R<Void> sort(TaskSortBo sortBo) {

        TaskPoolId poolId = new TaskPoolId(sortBo.getPoolId());

        List<TaskId> taskIdList = sortBo.getTaskIds().stream()
                .map(TaskId::new)
                .collect(Collectors.toList());

        taskService.sort(poolId, taskIdList);

        return R.ok();
    }

    /**
     * 用于页面的首次获取
     */
    @NeedLogin
    public R<List<TaskPoolResult>> queryLists() {

        AccountId currentAccountId = securityService.getCurrentAccountId();

        Paging paging = new Paging(0L, 10);

        List<TaskPoolResult> list = TaskPool.defaultPool().stream().map(pool -> {

            List<Task> tasks = taskRepository.listByPoolId(currentAccountId, pool.getId(), paging);
            return converter.convert(tasks);

        }).collect(Collectors.toList());

        return R.ok(list);
    }

    @NeedLogin
    public R<TaskPoolResult> queryList(TaskListBo bo) {

        AccountId currentAccountId = securityService.getCurrentAccountId();

        TaskPoolId poolId = new TaskPoolId(bo.getPoolId());

        List<Task> tasks = taskRepository.listByPoolId(currentAccountId, poolId, bo.toPaging());
        TaskPoolResult ret = converter.convert(tasks);

        return R.ok(ret);
    }

    @NeedLogin
    public R<TaskDetailResult> queryDetail(Long id) {

        Task task = taskRepository.getById(new TaskId(id));
        if (task == null) {
            throw new BusinessException(ErrorCodeEnum.NOT_FOUND);
        }

        TaskDetailResult result = converter.toDetail(task);

        return R.ok(result);
    }

}
