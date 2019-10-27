package com.xiaohe66.web.aop;

import com.github.pagehelper.PageHelper;
import com.xiaohe66.web.base.data.Final;
import com.xiaohe66.web.base.util.StrUtils;
import com.xiaohe66.web.base.util.WebUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * 通用分页切面
 *
 * @author xiaohe
 * @time 17-11-11 011
 * @deprecated 使用了mybatis-plus 中的分页了，这个分页插件不再使用
 */
@Aspect
@Deprecated
public class XhPagingAsp {

    private static final Logger LOGGER = LoggerFactory.getLogger(XhPagingAsp.class);

    private static final int DEFAULT_PAGE_NUM = 1;
    private static final int DEFAULT_PAGE_SIZE = 10;

    @Pointcut("@annotation(com.xiaohe66.web.base.annotation.Paging)")
    private void pagingPointCut() {
    }

    @Before("pagingPointCut()")
    public void beforeAdvice() {
        HttpServletRequest request = WebUtils.getRequest();

        String pageSizeStr = request.getHeader(Final.Str.PAGING_SIZE_KEY);
        int size = StrUtils.isEmpty(pageSizeStr) ? DEFAULT_PAGE_SIZE : StrUtils.toInt(pageSizeStr);

        String pageNumStr = request.getHeader(Final.Str.PAGING_NO_KEY);
        int num = StrUtils.isEmpty(pageNumStr) ? DEFAULT_PAGE_NUM : StrUtils.toInt(pageNumStr);

        PageHelper.startPage(num, size, true);
    }
}
