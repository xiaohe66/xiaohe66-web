package com.xiaohe66.web.domain.file.value;

import com.xiaohe66.common.util.IdWorker;
import org.junit.Test;

public class ImageIdTest {

    @Test
    public void test1() {

        ImageId imageId = new ImageId(IdWorker.genId());

        System.out.println(imageId.takeAbsolutePath());

    }
}