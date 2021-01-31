package com.xiaohe66.web.code.security.dto;

import com.xiaohe66.web.base.base.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaohe
 * @time 2019.10.21 10:56
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RoleDto extends BaseDto {

    private String roleName;
    private String roleDesc;
    private Boolean isDefault;

}
