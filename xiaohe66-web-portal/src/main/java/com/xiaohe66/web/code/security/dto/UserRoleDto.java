package com.xiaohe66.web.code.security.dto;

import com.xiaohe66.web.base.base.BaseDto;
import lombok.Data;

/**
 * @author xiaohe
 * @time 2019.10.21 11:19
 */
@Data
public class UserRoleDto extends BaseDto {

    private String roleName;
    private Boolean isChecked;


}
