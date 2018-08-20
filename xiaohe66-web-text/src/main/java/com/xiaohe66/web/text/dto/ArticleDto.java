package com.xiaohe66.web.text.dto;

import com.xiaohe66.web.base.base.BaseDtoDetailed;

/**
 * @author xiaohe
 * @time 17-11-11 011
 */
public class ArticleDto extends BaseDtoDetailed{

    private String text;
    private String title;
    private Long sysCategoryId;
    private String sysCategoryName;
    private String perCategoryIds;
    private String perCategoryNames;
    private Long imgFileId;
    private String usrName;
    private Long count;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSysCategoryName() {
        return sysCategoryName;
    }

    public void setSysCategoryName(String sysCategoryName) {
        this.sysCategoryName = sysCategoryName;
    }

    public String getPerCategoryNames() {
        return perCategoryNames;
    }

    public void setPerCategoryNames(String perCategoryNames) {
        this.perCategoryNames = perCategoryNames;
    }

    public Long getSysCategoryId() {
        return sysCategoryId;
    }

    public void setSysCategoryId(Long sysCategoryId) {
        this.sysCategoryId = sysCategoryId;
    }

    public String getPerCategoryIds() {
        return perCategoryIds;
    }

    public void setPerCategoryIds(String perCategoryIds) {
        this.perCategoryIds = perCategoryIds;
    }

    public String getUsrName() {
        return usrName;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }

    public Long getImgFileId() {
        return imgFileId;
    }

    public void setImgFileId(Long imgFileId) {
        this.imgFileId = imgFileId;
    }


    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "{" + "\"text\":\"" + text + "\""
                + ",\"createId\":\"" + createId + "\""
                + ",\"id\":\"" + id + "\""
                + ",\"title\":\"" + title + "\""
                + ",\"createTime\":\"" + createTime + "\""
                + ",\"sysCategoryId\":\"" + sysCategoryId + "\""
                + ",\"updateId\":\"" + updateId + "\""
                + ",\"sysCategoryName\":\"" + sysCategoryName + "\""
                + ",\"updateTime\":\"" + updateTime + "\""
                + ",\"perCategoryIds\":\"" + perCategoryIds + "\""
                + ",\"isDelete\":\"" + isDelete + "\""
                + ",\"perCategoryNames\":\"" + perCategoryNames + "\""
                + ",\"imgFileId\":\"" + imgFileId + "\""
                + ",\"usrName\":\"" + usrName + "\""
                + ",\"count\":\"" + count + "\""
                + "}";
    }
}
