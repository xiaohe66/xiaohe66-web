package com.xiaohe66.web.code.file.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaohe66.web.base.base.BasePoDetailed;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaohe
 * @time 17-11-11 011
 */
@EqualsAndHashCode(callSuper = true)
@TableName("xiaohe66_web_org_user_file")
@Data
public class UserFile extends BasePoDetailed {

    private String fileName;
    private String fileDesc;
    private Integer fileType;
    private Integer fileId;
    private String extension;

    public UserFile() {
    }

    public UserFile(Integer id, String fileName) {
        super.id = id;
        this.fileName = fileName;
    }

    public static class FileType {

        public static final int DEFAULT_FILE = 0;
        public static final int USER_HEAD_IMG = 1;
        public static final int USER_ARTICLE_IMG = 2;

        private FileType() {
        }
    }

}
