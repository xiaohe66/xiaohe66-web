package com.xiaohe66.web.resolver;

import com.xiaohe66.web.common.data.StrEnum;
import com.xiaohe66.web.org.dto.UsrDto;
import com.xiaohe66.web.sys.dto.CurrentUsr;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author xiaohe
 * @time 17-08-04 004
 */
public class CurrentUsrResolver implements HandlerMethodArgumentResolver{
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(CurrentUsr.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        Subject currentUser = SecurityUtils.getSubject();
        UsrDto usr = (UsrDto)currentUser.getSession().getAttribute(StrEnum.SESSION_UER_KEY.data());
        return new CurrentUsr(usr);
    }
}
