package com.xiaohe66.web.integration;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author xiaohe
 * @since 2021.11.01 10:37
 */
@Data
public class BaseLongInputDo implements IDo {

    @TableId(type = IdType.INPUT)
    private Long id;

}
