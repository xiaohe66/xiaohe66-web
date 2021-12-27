package com.xiaohe66.web.domain.task.service;

import com.xiaohe66.web.domain.sys.sec.service.SecurityService;
import com.xiaohe66.web.domain.task.agg.Task;
import com.xiaohe66.web.domain.task.repository.TaskRepository;
import com.xiaohe66.web.domain.task.value.TaskChangeTime;
import com.xiaohe66.web.domain.task.value.TaskId;
import com.xiaohe66.web.domain.task.value.TaskPoolId;
import com.xiaohe66.web.domain.task.value.TaskSort;
import com.xiaohe66.web.integration.ex.BusinessException;
import com.xiaohe66.web.integration.ex.ErrorCodeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author xiaohe
 * @since 2021.11.17 14:17
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    private final SecurityService securityService;

    public void saveTask(Task task) {

        Task dbTask = taskRepository.getById(task.getId());

        if (dbTask != null && !dbTask.getPoolId().equals(task.getPoolId())) {
            task.setChangeTime(new TaskChangeTime(LocalDateTime.now()));
        }

        taskRepository.save(task);
    }

    public void removeById(TaskId id) {

        Task task = taskRepository.getById(id);

        if (task == null) {
            throw new BusinessException(ErrorCodeEnum.NOT_FOUND);
        }

        securityService.checkCreatorPermission(task.getCreateId());
        taskRepository.removeById(id);
    }

    public void changePool(TaskId taskId, TaskPoolId poolId) {

        Task task = taskRepository.getById(taskId);
        if (task == null) {
            throw new BusinessException(ErrorCodeEnum.NOT_FOUND);
        }
        task.changePool(poolId);

        taskRepository.save(task);
    }

    @Transactional(rollbackFor = Exception.class)
    public void sort(TaskPoolId poolId, List<TaskId> taskIdList) {

        // 默认排序是0, 自定义排序会从1开始,这样新建的就会放在前面
        int sort = 1;
        for (TaskId taskId : taskIdList) {
            Task task = taskRepository.getById(taskId);

            // 可修改: 拥有创建者权限且待办池相同
            boolean canSave = securityService.hasCreatorPermission(task.getCreateId()) &&
                    poolId.equals(task.getPoolId());

            if (!canSave) {
                continue;
            }

            task.changeSort(TaskSort.valueOf(sort));
            taskRepository.save(task);

            sort++;
        }
    }

}
