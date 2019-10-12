package com.xiaohe66.web.code.love.dto;

import com.xiaohe66.web.base.base.BaseDtoDetailed;

import java.util.List;

/**
 * @author xiaohe
 * @time 2019.10.11 18:24
 */
public class PhotoDto extends BaseDtoDetailed {

    private String name;

    private List<String> photoDescList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPhotoDescList() {
        return photoDescList;
    }

    public void setPhotoDescList(List<String> photoDescList) {
        this.photoDescList = photoDescList;
    }
}
