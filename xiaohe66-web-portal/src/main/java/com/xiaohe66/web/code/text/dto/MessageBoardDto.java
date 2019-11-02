package com.xiaohe66.web.code.text.dto;

import com.xiaohe66.web.base.base.BaseDtoDetailed;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xh
 * @time 18-04-01 001
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MessageBoardDto extends BaseDtoDetailed{
    private String msg;
    private String userName;
    private Integer imgFileId;
    private String anonymity;
}
