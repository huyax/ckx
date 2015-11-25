package com.ckx.api.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/**
 * 自定义异常处理
 */
public class ExceptionResolver extends SimpleMappingExceptionResolver {

    private Log log = LogFactory.getLog(getClass());

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) {
        if (response.isCommitted()) {
            return null;
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append("<div>" + ex.getClass() + ":" + ex.getMessage() + "</div>");
        StackTraceElement stacks[] = ex.getStackTrace();
        for (StackTraceElement stack : stacks) {
            buffer.append("<div style='padding-left: 40px;'>" + stack.toString() + "</div>");
        }
        request.setAttribute("ex_msg", buffer);
        log.error(
                "public event ExceptionEventHandler ExceptionOccurrs!!!!!!!!!!!!![request IP address:"
                        + request.getRemoteAddr() + "][request URL:" + request.getRequestURI() + "]", ex);
        return super.doResolveException(request, response, handler, ex);
    }
    
}
