package com.xiaohe66.web.infrastructure.mybatis.love.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaohe66.web.integration.BaseLongInputDo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaohe
 * @since 2021.12.07 11:46
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("love_message")
public class MessageDo extends BaseLongInputDo {

    private Long loverId;

    private String text;

}
