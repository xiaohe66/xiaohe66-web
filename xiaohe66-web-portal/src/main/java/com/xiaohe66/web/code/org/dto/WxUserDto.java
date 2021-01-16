package com.xiaohe66.web.code.org.dto;

import com.xiaohe66.web.base.base.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaohe
 * @time 2019.12.06 16:23
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WxUserDto extends BaseDto {

    private String nickname;
    private String token;
    private String sexStr;
}
