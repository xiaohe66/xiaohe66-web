package com.xiaohe66.web.code.file.dto;

import com.xiaohe66.web.base.base.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xh
 * @time 2019-10-28 23:41
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserFileDto extends BaseDto {

    private String fileName;
    private String extension;
    private String createTime;
    private String fileSize;
    private String createUserName;

}
