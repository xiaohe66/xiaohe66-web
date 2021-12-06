package com.xiaohe66.web.domain.file.agg;

import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.file.value.ImageId;
import com.xiaohe66.web.domain.file.value.ImageName;
import com.xiaohe66.web.integration.domain.Aggregate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.io.InputStream;

/**
 * @author xiaohe
 * @since 2021.12.02 18:27
 */
@Builder
@Getter
@ToString(exclude = "inputStream")
@AllArgsConstructor
public class Image implements Aggregate<Image, ImageId> {

    @NonNull
    private final ImageId id;

    @NonNull
    private final AccountId createId;

    @NonNull
    private final ImageName name;

    private final InputStream inputStream;

    public String getAbsolutePath() {
        return id.takeAbsolutePath();
    }

    @Override
    public boolean hasSameRootAttribute(Image other) {
        return other != null;
    }

}
