package com.xiaohe66.web.domain.file.agg;

import com.xiaohe66.web.domain.file.value.PictureCategory;
import com.xiaohe66.web.domain.file.value.PictureId;
import com.xiaohe66.web.domain.file.value.PicturePath;
import com.xiaohe66.web.integration.domain.Aggregate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * @author xiaohe
 * @since 2021.12.23 21:18
 */
@Builder
@Getter
@ToString
@AllArgsConstructor
public class Picture implements Aggregate<Picture, PictureId> {

    private final PictureId id;

    private final PictureCategory category;

    private final PicturePath path;

    @Override
    public boolean hasSameRootAttribute(Picture other) {
        return other != null;
    }
}
