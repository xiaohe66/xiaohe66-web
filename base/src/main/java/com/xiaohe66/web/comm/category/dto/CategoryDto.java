package com.xiaohe66.web.comm.category.dto;

import com.xiaohe66.web.common.base.BaseDtoDetailed;

/**
 * @author xiaohe
 * @time 17-11-12 012
 */
public class CategoryDto extends BaseDtoDetailed {

    private String categoryName;
    private String categoryDesc;
    private String pid;

    public CategoryDto(){}

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "{" + "\"createId\":\"" + createId + "\""
                + ",\"id\":\"" + id + "\""
                + ",\"createTime\":" + createTime
                + ",\"updateId\":\"" + updateId + "\""
                + ",\"updateTime\":" + updateTime
                + ",\"isDelete\":\"" + isDelete + "\""
                + ",\"categoryName\":\"" + categoryName + "\""
                + ",\"categoryDesc\":\"" + categoryDesc + "\""
                + ",\"pid\":\"" + pid + "\""
                + "}";
    }
}
