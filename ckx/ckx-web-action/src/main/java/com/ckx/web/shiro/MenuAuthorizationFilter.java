package com.ckx.web.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.springframework.stereotype.Component;

/**
 * 菜单授权认证
 */
@Component
public class MenuAuthorizationFilter extends AuthorizationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
            throws Exception {
        HttpServletRequest req = (HttpServletRequest) request;
        String menu = req.getRequestURI().replaceAll(req.getContextPath(), "");
        return getSubject(request, response).isPermitted(menu);
    }
}
