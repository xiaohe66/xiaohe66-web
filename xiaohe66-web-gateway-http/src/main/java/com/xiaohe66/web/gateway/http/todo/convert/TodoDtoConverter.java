package com.xiaohe66.web.gateway.http.todo.convert;

import com.xiaohe66.web.application.todo.bo.TodoChangePoolBo;
import com.xiaohe66.web.application.todo.bo.TodoListBo;
import com.xiaohe66.web.application.todo.bo.TodoSaveBo;
import com.xiaohe66.web.application.todo.bo.TodoSortBo;
import com.xiaohe66.web.gateway.http.DtoConverter;
import com.xiaohe66.web.gateway.http.todo.dto.TodoChangePoolDto;
import com.xiaohe66.web.gateway.http.todo.dto.TodoListDto;
import com.xiaohe66.web.gateway.http.todo.dto.TodoSaveDto;
import com.xiaohe66.web.gateway.http.todo.dto.TodoSortDto;
import org.mapstruct.Mapper;

/**
 * @author xiaohe
 * @since 2021.11.17 15:19
 */
@Mapper(componentModel = "spring")
public interface TodoDtoConverter extends DtoConverter {

    TodoSaveBo toBo(TodoSaveDto dto);

    TodoChangePoolBo toBo(TodoChangePoolDto dto);

    TodoSortBo toBo(TodoSortDto dto);

    TodoListBo toBo(TodoListDto dto);
}
