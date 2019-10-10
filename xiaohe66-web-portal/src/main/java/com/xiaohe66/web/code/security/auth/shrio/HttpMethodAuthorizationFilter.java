package com.xiaohe66.web.code.security.auth.shrio;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author xh
 * @date 18-04-25 025
 */
public class HttpMethodAuthorizationFilter extends FormAuthenticationFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        String method = ((HttpServletRequest) request).getMethod();

        String[] httpMethods = (String[]) mappedValue;
        for (String httpMethod : httpMethods) {
            if(httpMethod.equalsIgnoreCase(method)){
                return true;
            }
        }
        return super.isAccessAllowed(request, response, mappedValue);
    }

}
