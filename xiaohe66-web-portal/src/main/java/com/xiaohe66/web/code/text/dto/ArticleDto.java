package com.xiaohe66.web.code.text.dto;

import com.xiaohe66.web.base.base.BaseDtoDetailed;

/**
 * @author xiaohe
 * @time 17-11-11 011
 */
public class ArticleDto extends BaseDtoDetailed{

    private String text;
    private String title;
    private Boolean isPublish;
    private Integer secretLevel;
    private Integer sysCategoryId;
    private String sysCategoryName;
    private String perCategoryIds;
    private String perCategoryNames;
    private Integer imgFileId;
    private String usrName;
    private Integer count;

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

    public Boolean getIsPublish() {
        return isPublish;
    }

    public void setIsPublish(Boolean publish) {
        isPublish = publish;
    }

    public Integer getSecretLevel() {
        return secretLevel;
    }

    public void setSecretLevel(Integer secretLevel) {
        this.secretLevel = secretLevel;
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

    public Integer getSysCategoryId() {
        return sysCategoryId;
    }

    public void setSysCategoryId(Integer sysCategoryId) {
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

    public Integer getImgFileId() {
        return imgFileId;
    }

    public void setImgFileId(Integer imgFileId) {
        this.imgFileId = imgFileId;
    }


    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
