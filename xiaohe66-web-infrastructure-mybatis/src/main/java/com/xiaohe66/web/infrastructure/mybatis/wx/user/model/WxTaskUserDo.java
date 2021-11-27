package com.xiaohe66.web.infrastructure.mybatis.wx.user.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaohe66.web.integration.BaseLongInputDo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaohe
 * @since 2021.10.28 11:55
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("wx_Task_user")
public class WxTaskUserDo extends BaseLongInputDo {

    private String openId;

}
