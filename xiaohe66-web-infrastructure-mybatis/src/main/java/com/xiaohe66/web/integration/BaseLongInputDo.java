package com.xiaohe66.web.integration;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author xiaohe
 * @since 2021.11.01 10:37
 */
@Data
public class BaseLongInputDo implements IDo {

    @TableId(type = IdType.INPUT)
    private Long id;

    private Long createId;

    private LocalDateTime updateTime;

    @TableLogic
    private Boolean deleted;

}
