package com.turing.frame.httplog.filter;

import com.turing.frame.httplog.BodyReaderHttpServletRequestWrapper;
import com.turing.frame.httplog.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * POST请求缓存
 *
 * @author weizhiwen
 * @date 2018/9/17
 */
@Slf4j
public class PostJsonRequestBodyCacheFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        if (!(servletRequest instanceof BodyReaderHttpServletRequestWrapper) && isPostJson(httpServletRequest)) {

            log.debug("ServletRequest Convert {} to {}", servletRequest.getClass(), BodyReaderHttpServletRequestWrapper.class);
            servletRequest = new BodyReaderHttpServletRequestWrapper(httpServletRequest);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    protected boolean isPostJson(HttpServletRequest httpServletRequest) {
        //是POST请求，且content-type:application/json
        return StringUtils.equalsIgnoreCase(httpServletRequest.getMethod(), Constants.METHOD_POST) && StringUtils.containsIgnoreCase(httpServletRequest.getContentType(), Constants.APPLICATION_JSON_VALUE);
    }

    public void destroy() {

    }
}
