package com.xiaohe66.web.test.base;

import com.xiaohe66.web.base.exception.XhWebException;
import com.xiaohe66.web.base.util.StrUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author xiaohe
 * @time 18-01-22 022
 */
public class StrUtilsTest {

    @Test
    public void isEmptyTest(){
        assertEquals(true, StrUtils.isEmpty(null));
        assertEquals(true, StrUtils.isEmpty(""));
        assertEquals(false, StrUtils.isEmpty("1"));
    }

    @Test
    public void isNotEmptyTest(){
        assertEquals(false, StrUtils.isNotEmpty(null));
        assertEquals(false, StrUtils.isNotEmpty(""));
        assertEquals(true, StrUtils.isNotEmpty("1"));
    }

    @Test
    public void isAllNotEmptyTest(){
        assertEquals(false,StrUtils.isAllNotEmpty());
        assertEquals(false,StrUtils.isAllNotEmpty(null));
        assertEquals(false,StrUtils.isAllNotEmpty(""));
        assertEquals(true,StrUtils.isAllNotEmpty("1"));
        assertEquals(false,StrUtils.isAllNotEmpty("1",null));
        assertEquals(false,StrUtils.isAllNotEmpty("1",""));
        assertEquals(false,StrUtils.isAllNotEmpty("1",""));
        assertEquals(true,StrUtils.isAllNotEmpty("1","2"));
    }

    @Test
    public void isOneEmptyTest(){
        assertEquals(true,StrUtils.isOneEmpty());
        assertEquals(true,StrUtils.isOneEmpty(null));
        assertEquals(true,StrUtils.isOneEmpty(""));
        assertEquals(false,StrUtils.isOneEmpty("1"));
        assertEquals(true,StrUtils.isOneEmpty("1",null));
        assertEquals(true,StrUtils.isOneEmpty("1",""));
        assertEquals(true,StrUtils.isOneEmpty("1",""));
        assertEquals(false,StrUtils.isOneEmpty("1","2"));
    }


    @Test
    public void equalsAndNotNullTest(){
        assertEquals(false,StrUtils.equalsAndNotEmpty(null,null));
        assertEquals(false,StrUtils.equalsAndNotEmpty(null,""));
        assertEquals(false,StrUtils.equalsAndNotEmpty("",null));
        assertEquals(false,StrUtils.equalsAndNotEmpty("",""));
        assertEquals(false,StrUtils.equalsAndNotEmpty("1",""));
        assertEquals(false,StrUtils.equalsAndNotEmpty("","1"));
        assertEquals(true,StrUtils.equalsAndNotEmpty("1","1"));
    }

    @Test
    public void toIntNotExceptionTest(){
        assertEquals(null,StrUtils.toIntNotException(""));
        assertEquals(null,StrUtils.toIntNotException("x"));
        assertEquals(null,StrUtils.toIntNotException("1."));
        assertEquals(new Integer(1),StrUtils.toIntNotException("1"));
        assertEquals(new Integer(12),StrUtils.toIntNotException("12"));
    }

    @Test(expected = XhWebException.class)
    public void toIntTest1(){
        StrUtils.toInt("");
    }
    @Test(expected = XhWebException.class)
    public void toIntTest2(){
        StrUtils.toInt("x");
    }
    @Test(expected = XhWebException.class)
    public void toIntTest3(){
        StrUtils.toInt("1.");
    }
    @Test
    public void toIntTest4(){
        assertEquals(12,StrUtils.toInt("12"));
        assertEquals(0,StrUtils.toInt("0"));
    }

    @Test
    public void toLongNotExceptionTest(){
        assertEquals(null,StrUtils.toLongNotException(""));
        assertEquals(null,StrUtils.toLongNotException("x"));
        assertEquals(null,StrUtils.toLongNotException("1."));
        assertEquals(new Long(1),StrUtils.toLongNotException("1"));
        assertEquals(new Long(12),StrUtils.toLongNotException("12"));
    }

    @Test(expected = XhWebException.class)
    public void toLong1(){
        StrUtils.toLong("");
    }
    @Test(expected = XhWebException.class)
    public void toLong2(){
        StrUtils.toLong("x");
    }
    @Test(expected = XhWebException.class)
    public void toLong3(){
        StrUtils.toLong("1.");
    }
    @Test
    public void toLong4(){
        assertEquals(12,StrUtils.toLong("12"));
        assertEquals(0,StrUtils.toLong("0"));
    }

    @Test
    public void trimTest(){
        assertEquals("",StrUtils.trim("      "));
        assertEquals("1",StrUtils.trim("   1   "));
        assertEquals("12",StrUtils.trim("   12   "));
        assertEquals("12",StrUtils.trim("12   "));
        assertEquals("12",StrUtils.trim("   12"));
        assertEquals("12  3",StrUtils.trim("   12  3 "));
        assertEquals("12  3",StrUtils.trim("12  3 "));
        assertEquals("12  3",StrUtils.trim("   12  3"));
    }
}
