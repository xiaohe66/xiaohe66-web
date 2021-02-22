package com.xiaohe66.web.code.org.dto;

import com.xiaohe66.web.base.base.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaohe
 * @time 2021.02.22 11:41
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserDto extends BaseDto {

    private String userName;
    private String email;

    private String wxNickname;

}
