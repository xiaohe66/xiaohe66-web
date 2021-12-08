package com.xiaohe66.web.infrastructure.mybatis.file.image.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaohe66.web.integration.BaseLongInputDo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaohe
 * @since 2021.12.03 17:13
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("file_image")
public class ImageDo extends BaseLongInputDo {

    @TableField("`name`")
    private String name;

}
