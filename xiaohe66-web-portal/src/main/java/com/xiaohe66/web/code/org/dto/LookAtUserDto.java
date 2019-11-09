package com.xiaohe66.web.code.org.dto;

import com.xiaohe66.web.base.base.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author xiaohe
 * @time 2019.10.16 09:40
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class LookAtUserDto extends BaseDto {

    private String userName;
    private String signature;
    private Integer imgFileId;
}
