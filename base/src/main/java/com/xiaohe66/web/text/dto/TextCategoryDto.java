package com.xiaohe66.web.text.dto;

import com.xiaohe66.web.common.base.BaseDto;

/**
 * @author xiaohe
 * @time 17-11-12 012
 */
public class TextCategoryDto extends BaseDto {

    private String categoryName;

    public TextCategoryDto(){}

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "{" + "\"categoryName\":\"" + categoryName + "\""
                + ",\"id\":\"" + id + "\""
                + "}";
    }
}
