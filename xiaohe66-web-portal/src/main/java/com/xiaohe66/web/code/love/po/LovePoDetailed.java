package com.xiaohe66.web.code.love.po;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author xiaohe
 * @time 2020.01.07 12:28
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LovePoDetailed extends LovePo {

    private Integer createId;
    private Date createTime;
    private Integer updateId;
    private Date updateTime;

    @TableLogic
    private Boolean isDelete;

}
