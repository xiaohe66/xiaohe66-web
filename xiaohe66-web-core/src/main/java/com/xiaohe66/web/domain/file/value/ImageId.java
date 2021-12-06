package com.xiaohe66.web.domain.file.value;

import com.xiaohe66.common.util.IdWorker;
import com.xiaohe66.web.integration.domain.LongId;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.File;
import java.time.LocalDateTime;

/**
 * @author xiaohe
 * @since 2021.12.02 18:25
 */
@EqualsAndHashCode(callSuper = true)
@ToString
public class ImageId extends LongId {

    private String absolutePath;

    public ImageId(Long value) {
        super(value);
    }

    public String takeAbsolutePath() {
        if (absolutePath == null) {
            LocalDateTime localDateTime = IdWorker.takeLocalDateTime(getValue());

            absolutePath = File.separator +
                    localDateTime.getYear() + "-" + localDateTime.getMonthValue() +
                    File.separator +
                    getValue();
        }
        return absolutePath;
    }
}
