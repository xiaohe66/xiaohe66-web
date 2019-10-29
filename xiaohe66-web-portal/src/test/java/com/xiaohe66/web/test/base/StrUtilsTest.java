package com.xiaohe66.web.test.base;

import com.xiaohe66.web.base.exception.XhWebException;
import com.xiaohe66.web.base.util.StrUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author xiaohe
 * @time 18-01-22 022
 */
public class StrUtilsTest {


    @Test
    public void equalsAndNotNullTest() {
        assertFalse(StrUtils.equalsAndNotEmpty(null, null));
        assertFalse(StrUtils.equalsAndNotEmpty(null, ""));
        assertFalse(StrUtils.equalsAndNotEmpty("", null));
        assertFalse(StrUtils.equalsAndNotEmpty("", ""));
        assertFalse(StrUtils.equalsAndNotEmpty("1", ""));
        assertFalse(StrUtils.equalsAndNotEmpty("", "1"));
        assertTrue(StrUtils.equalsAndNotEmpty("1", "1"));
    }

    @Test
    public void toIntNotExceptionTest() {
        assertEquals(0, StrUtils.toIntNotException(""));
        assertEquals(0, StrUtils.toIntNotException("x"));
        assertEquals(0, StrUtils.toIntNotException("1."));
        assertEquals(1, StrUtils.toIntNotException("1"));
        assertEquals(2, StrUtils.toIntNotException("12"));
    }

    @Test(expected = XhWebException.class)
    public void toIntTest1() {
        StrUtils.toInt("");
    }

    @Test(expected = XhWebException.class)
    public void toIntTest2() {
        StrUtils.toInt("x");
    }

    @Test(expected = XhWebException.class)
    public void toIntTest3() {
        StrUtils.toInt("1.");
    }

    @Test
    public void toIntTest4() {
        assertEquals(12, StrUtils.toInt("12"));
        assertEquals(0, StrUtils.toInt("0"));
    }

    @Test
    public void trimTest() {
        assertEquals("", StrUtils.trim("      "));
        assertEquals("1", StrUtils.trim("   1   "));
        assertEquals("12", StrUtils.trim("   12   "));
        assertEquals("12", StrUtils.trim("12   "));
        assertEquals("12", StrUtils.trim("   12"));
        assertEquals("12  3", StrUtils.trim("   12  3 "));
        assertEquals("12  3", StrUtils.trim("12  3 "));
        assertEquals("12  3", StrUtils.trim("   12  3"));
    }
}
