package com.xiaohe66.web.test.base;

import com.xiaohe66.web.base.exception.XhIoException;
import com.xiaohe66.web.base.util.IoUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * @author xh
 * @version 1.0
 * @time 2018-07-10 09:40
 */
public class IoUtilsTest {

    private static final String PATH = "D:\\IoUtilsTest";

    public void testReadWithClassPath() throws XhIoException {
        String filePath = "com/xiaohe66/web/base/util/IoUtils.class";
        String content = IoUtils.readStringInClassPath(filePath);
        System.out.println(content);
    }

    @Test
    public void test1() throws IOException {
        File file = new File(PATH + "\\tmp\\test1");
        file.mkdirs();
        file = new File(PATH);
        IoUtils.delete(file);
        assertEquals(false, file.exists());
    }


    @Test
    public void test2() throws IOException {
        File file = new File(PATH);
        file.createNewFile();
        assertEquals(true, file.exists());
        IoUtils.delete(file);
        assertEquals(false, file.exists());
    }

    @Test(expected = NullPointerException.class)
    public void test3() throws IOException {
        File file = null;
        IoUtils.delete(file);
    }
}
