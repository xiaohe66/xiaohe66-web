package com.xiaohe66.web.category.dto;

import com.xiaohe66.web.common.base.BaseDto;
import com.xiaohe66.web.common.data.CheckUtils;
import com.xiaohe66.web.common.data.CodeEnum;
import com.xiaohe66.web.common.exception.XhException;
import com.xiaohe66.web.category.po.Category;

/**
 * @author xiaohe
 * @time 17-11-12 012
 */
public class CategoryDto extends BaseDto {

    private String cName;
    private String cDesc;
    private String pId;

    public CategoryDto(){}

    public CategoryDto(Category category){
        if(CheckUtils.isNull(category)){
            throw new XhException(CodeEnum.NULL_EXCEPTION);
        }
        this.cName = category.getcName();
        this.cDesc = category.getCDesc();
        this.pId = category.getPId();
        this.id = category.getId();
    }

    @Override
    public String toString() {
        return "{" + "\"id\":\"" + id + "\""
                + ", \"cName\":\"" + cName + "\""
                + ", \"cDesc\":\"" + cDesc + "\""
                + ", \"pId\":\"" + pId + "\""
                + "}";
    }
}
