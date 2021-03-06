package com.xiaohe66.web.code.text.po;


import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaohe66.web.base.base.BasePoDetailed;

/**
 * @author xiaohe
 * @time 17-11-11 011
 */
@TableName("xiaohe66_web_text_category")
public class TextCategory extends BasePoDetailed {

    private String categoryName;
    private String categoryDesc;
    private String pid;

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
                + ",\"categoryName\":\"" + categoryName + "\""
                + ",\"createTime\":" + createTime
                + ",\"id\":\"" + id + "\""
                + ",\"categoryDesc\":\"" + categoryDesc + "\""
                + ",\"updateId\":\"" + updateId + "\""
                + ",\"updateTime\":" + updateTime
                + ",\"pid\":\"" + pid + "\""
                + ",\"isDelete\":\"" + isDelete + "\""
                + "}";
    }
}
