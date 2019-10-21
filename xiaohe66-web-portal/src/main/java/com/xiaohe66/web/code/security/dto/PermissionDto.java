package com.xiaohe66.web.code.security.dto;

import com.xiaohe66.web.base.base.BaseDtoDetailed;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaohe
 * @time 2019.10.21 10:40
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PermissionDto extends BaseDtoDetailed {

    private String name;
    private String desc;
}
