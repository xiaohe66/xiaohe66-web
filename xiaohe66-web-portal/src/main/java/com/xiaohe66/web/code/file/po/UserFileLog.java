package com.xiaohe66.web.code.file.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaohe66.web.base.base.BasePo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author xh
 * @time 18-04-15 015
 */
@EqualsAndHashCode(callSuper = true)
@TableName("xiaohe66_web_org_user_file_log")
@Data
public class UserFileLog extends BasePo {

    private Integer createId;
    private Date createTime;
    private Integer logType;
    private Integer userFileId;
    private String ip;

    public UserFileLog(Integer userFileId) {
        this.userFileId = userFileId;
    }
}
