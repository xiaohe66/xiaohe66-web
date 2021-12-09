package com.xiaohe66.web.domain.wx.user.value;

import com.xiaohe66.common.util.IdWorker;
import com.xiaohe66.web.integration.domain.LongId;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.File;
import java.time.LocalDateTime;

/**
 * @author xiaohe
 * @since 2021.08.11 17:47
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WxUserId extends LongId {

    private String absolutePath;

    public WxUserId(long value) {
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
