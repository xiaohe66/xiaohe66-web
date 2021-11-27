package com.xiaohe66.web.gateway.http.task.convert;

import com.xiaohe66.web.application.task.bo.TaskChangePoolBo;
import com.xiaohe66.web.application.task.bo.TaskListBo;
import com.xiaohe66.web.application.task.bo.TaskSaveBo;
import com.xiaohe66.web.application.task.bo.TaskSortBo;
import com.xiaohe66.web.gateway.http.DtoConverter;
import com.xiaohe66.web.gateway.http.task.dto.TaskChangePoolDto;
import com.xiaohe66.web.gateway.http.task.dto.TaskListDto;
import com.xiaohe66.web.gateway.http.task.dto.TaskSaveDto;
import com.xiaohe66.web.gateway.http.task.dto.TaskSortDto;
import org.mapstruct.Mapper;

/**
 * @author xiaohe
 * @since 2021.11.17 15:19
 */
@Mapper(componentModel = "spring")
public interface TaskDtoConverter extends DtoConverter {

    TaskSaveBo toBo(TaskSaveDto dto);

    TaskChangePoolBo toBo(TaskChangePoolDto dto);

    TaskSortBo toBo(TaskSortDto dto);

    TaskListBo toBo(TaskListDto dto);
}
