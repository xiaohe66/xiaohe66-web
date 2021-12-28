package com.xiaohe66.web.gateway.http.task;

import com.xiaohe66.common.dto.R;
import com.xiaohe66.web.application.task.TaskAppService;
import com.xiaohe66.web.application.task.bo.TaskChangePoolBo;
import com.xiaohe66.web.application.task.bo.TaskListBo;
import com.xiaohe66.web.application.task.bo.TaskSaveBo;
import com.xiaohe66.web.application.task.bo.TaskSortBo;
import com.xiaohe66.web.application.task.result.TaskDetailResult;
import com.xiaohe66.web.application.task.result.TaskPoolResult;
import com.xiaohe66.web.gateway.http.task.convert.TaskDtoConverter;
import com.xiaohe66.web.gateway.http.task.dto.TaskChangePoolDto;
import com.xiaohe66.web.gateway.http.task.dto.TaskListDto;
import com.xiaohe66.web.gateway.http.task.dto.TaskSaveDto;
import com.xiaohe66.web.gateway.http.task.dto.TaskSortDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xiaohe
 * @since 2021.11.17 14:19
 */
@RestController
@RequestMapping("/task")
@Slf4j
@RequiredArgsConstructor
public class TaskController {

    private final TaskDtoConverter dtoConverter;

    private final TaskAppService appService;

    /**
     * 保存, 不存在时新增，存在时更新，统一返回id
     */
    @PostMapping
    public R<Long> save(@RequestBody @Validated TaskSaveDto dto) {

        TaskSaveBo bo = dtoConverter.toBo(dto);
        return appService.save(bo);
    }

    @DeleteMapping("/{id}")
    public R<Void> remove(@PathVariable Long id) {
        return appService.remove(id);
    }

    @PostMapping("/changePool")
    public R<Void> changePool(@RequestBody @Validated TaskChangePoolDto dto) {

        TaskChangePoolBo changePoolBo = dtoConverter.toBo(dto);
        return appService.changeStatus(changePoolBo);
    }

    @PostMapping("/sort")
    public R<Void> sort(@RequestBody @Validated TaskSortDto dto) {
        TaskSortBo bo = dtoConverter.toBo(dto);
        return appService.sort(bo);
    }

    @GetMapping("/lists")
    public R<List<TaskPoolResult>> lists() {
        return appService.queryLists();
    }

    @GetMapping
    public R<TaskPoolResult> list(@Validated TaskListDto dto) {

        TaskListBo bo = dtoConverter.toBo(dto);
        return appService.queryList(bo);
    }

    @GetMapping("/{id}")
    public R<TaskDetailResult> detail(@PathVariable Long id) {
        return appService.queryDetail(id);
    }
}
