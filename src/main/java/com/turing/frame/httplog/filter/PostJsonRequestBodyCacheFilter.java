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
        if (isPostJson(httpServletRequest)) {
            //是POST请求，且content-type:application/json
            log.debug("ServletRequest Convert {} to {}", servletRequest.getClass(), BodyReaderHttpServletRequestWrapper.class);
            servletRequest = new BodyReaderHttpServletRequestWrapper(httpServletRequest);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    protected boolean isPostJson(HttpServletRequest httpServletRequest) {
        return StringUtils.equalsIgnoreCase(httpServletRequest.getMethod(), Constants.METHOD_POST) && StringUtils.isNotEmpty(httpServletRequest.getContentType()) && StringUtils.equalsIgnoreCase(StringUtils.split(httpServletRequest.getContentType(), Constants.MEDIA_TYPE_SEPARATOR)[0], Constants.APPLICATION_JSON_VALUE);
    }

    public void destroy() {

    }
}
