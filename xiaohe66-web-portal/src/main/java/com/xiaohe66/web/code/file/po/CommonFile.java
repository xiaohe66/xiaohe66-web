package com.xiaohe66.web.code.file.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaohe66.web.base.base.BasePo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author xh
 * @time 18-03-22 022
 */
@EqualsAndHashCode(callSuper = true)
@TableName("xiaohe66_web_comm_file")
@Data
public class CommonFile extends BasePo {

    protected String md5;

    // todo : rename to filePath
    protected String fileUrl;

    protected Long fileByte;

    protected Date startTime;

    protected Date endTime;

    public CommonFile() {
    }

    public CommonFile(String md5) {
        this.md5 = md5;
    }

}
