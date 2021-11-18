package com.xiaohe66.web.gateway.http.todo;

import com.xiaohe66.common.dto.R;
import com.xiaohe66.web.application.todo.TodoAppService;
import com.xiaohe66.web.application.todo.bo.TodoChangePoolBo;
import com.xiaohe66.web.application.todo.bo.TodoSaveBo;
import com.xiaohe66.web.application.todo.bo.TodoSortBo;
import com.xiaohe66.web.application.todo.result.TodoDetailResult;
import com.xiaohe66.web.application.todo.result.TodoListResult;
import com.xiaohe66.web.gateway.http.todo.convert.TodoDtoConverter;
import com.xiaohe66.web.gateway.http.todo.dto.TodoChangePoolDto;
import com.xiaohe66.web.gateway.http.todo.dto.TodoSaveDto;
import com.xiaohe66.web.gateway.http.todo.dto.TodoSortDto;
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

/**
 * @author xiaohe
 * @since 2021.11.17 14:19
 */
@RestController
@RequestMapping("/todo")
@Slf4j
@RequiredArgsConstructor
public class TodoController {

    private final TodoDtoConverter dtoConverter;

    private final TodoAppService todoAppService;

    /**
     * 保存, 不存在时新增，存在时更新，统一返回id
     */
    @PostMapping
    public R<Long> save(@RequestBody @Validated TodoSaveDto dto) {

        TodoSaveBo bo = dtoConverter.toBo(dto);
        return todoAppService.save(bo);
    }

    @DeleteMapping("/{id}")
    public R<Void> remove(@PathVariable Long id){
        return todoAppService.remove(id);
    }

    @PostMapping("/changePool")
    public R<Void> changePool(@RequestBody @Validated TodoChangePoolDto dto) {

        TodoChangePoolBo changePoolBo = dtoConverter.toBo(dto);
        return todoAppService.changeStatus(changePoolBo);
    }

    @PostMapping("/sort")
    public R<Void> sort(@RequestBody @Validated TodoSortDto dto) {
        TodoSortBo bo = dtoConverter.toBo(dto);
        return todoAppService.sort(bo);
    }

    @GetMapping
    public R<TodoListResult> list() {
        return todoAppService.queryList();
    }

    @GetMapping("/{id}")
    public R<TodoDetailResult> detail(@PathVariable Long id){
        return todoAppService.queryDetail(id);
    }
}
