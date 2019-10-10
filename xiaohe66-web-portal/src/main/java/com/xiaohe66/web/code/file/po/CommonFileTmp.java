package com.xiaohe66.web.code.file.po;

import com.xiaohe66.web.base.base.BasePo;

/**
 * 临时文件
 *
 * @author xh
 * @version 1.0
 * @time 2018-06-05 10:11
 */
public class CommonFileTmp extends BasePo {

    private String md5;
    private String fileUrl;
    private Integer fileByte;
    private Integer chunk;

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Integer getFileByte() {
        return fileByte;
    }

    public void setFileByte(Integer fileByte) {
        this.fileByte = fileByte;
    }

    public Integer getChunk() {
        return chunk;
    }

    public void setChunk(Integer chunk) {
        this.chunk = chunk;
    }
}
