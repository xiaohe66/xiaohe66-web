package com.xiaohe66.web.sys.aop;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

//    @Pointcut("@annotation(com.xiaohe66.web.base.annotation.Paging)")
    private void pagingPointCut() {
    }

//    @Before("pagingPointCut()")
    public void beforeAdvice() {
        /*HttpServletRequest request = WebUtils.getRequest();

        String pageSizeStr = request.getHeader(Final.Str.PAGING_SIZE_KEY);
        int size = Check.isEmpty(pageSizeStr) ? DEFAULT_PAGE_SIZE : StrUtils.toInt(pageSizeStr);

        String pageNumStr = request.getHeader(Final.Str.PAGING_NO_KEY);
        int num = Check.isEmpty(pageNumStr) ? DEFAULT_PAGE_NUM : StrUtils.toInt(pageNumStr);

        PageHelper.startPage(num, size, true);*/
    }
}
