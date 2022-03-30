package com.xiaohe66.web.infrastructure.mybatis.task.convert;

import com.xiaohe66.web.domain.task.agg.Task;
import com.xiaohe66.web.infrastructure.domain.adapter.task.TaskConverter;
import com.xiaohe66.web.infrastructure.mybatis.task.model.TaskDo;
import com.xiaohe66.web.integration.DoConverter;
import org.mapstruct.Mapper;

/**
 * @author xiaohe
 * @since 2021.11.17 11:53
 */
@Mapper
public interface TaskDoConverter extends DoConverter<Task, TaskDo>, TaskConverter {

}