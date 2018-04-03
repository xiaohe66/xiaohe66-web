package com.xiaohe66.web.aop;

import com.github.pagehelper.PageHelper;
import com.xiaohe66.web.spring.SpringUtils;
import com.xiaohe66.web.common.data.StrEnum;
import com.xiaohe66.web.common.util.StrUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 通用分页切面
 *
 * @author xiaohe
 * @time 17-11-11 011
 */
@Aspect
@Component
public class XhPagingAsp {

    private static final Logger LOGGER = LoggerFactory.getLogger(XhPagingAsp.class);

    private static final int DEFAULT_PAGE_NUM = 1;
    private static final int DEFAULT_PAGE_SIZE = 10;

    @Pointcut("@annotation(com.xiaohe66.web.common.annotation.Paging)")
    private void pagingPointCut(){}

    @Before("pagingPointCut()")
    public void beforeAdvice(){
        HttpServletRequest request = SpringUtils.getRequest();

        String pageSizeStr = request.getHeader(StrEnum.PAGING_SIZE_KEY.data());
        int size = StrUtils.isEmpty(pageSizeStr)?DEFAULT_PAGE_SIZE:StrUtils.toInt(pageSizeStr);

        String pageNumStr = request.getHeader(StrEnum.PAGING_NUM_KEY.data());
        int num = StrUtils.isEmpty(pageNumStr)?DEFAULT_PAGE_NUM:StrUtils.toInt(pageNumStr);

        PageHelper.startPage(num,size,true);
    }
}
