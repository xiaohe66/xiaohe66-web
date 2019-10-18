package com.xiaohe66.web.code.love.dto;

import com.xiaohe66.web.base.base.BaseDto;
import com.xiaohe66.web.base.base.BasePo;
import lombok.Data;

import java.util.List;
import java.util.Objects;

/**
 * @author xiaohe
 * @time 2019.10.13 17:00
 */
@Data
public class LovePhotoDto extends BaseDto {

    private Integer fileId;
    private String name;

    private List<String> photoDescList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LovePhotoDto that = (LovePhotoDto) o;
        return Objects.equals(fileId, that.fileId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(photoDescList, that.photoDescList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileId, name, photoDescList);
    }
}
