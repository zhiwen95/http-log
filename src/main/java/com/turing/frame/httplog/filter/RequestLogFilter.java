package com.turing.frame.httplog.filter;

import com.turing.frame.httplog.BodyReaderHttpServletRequestWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 输出请求日志
 *
 * @author weizhiwen
 * @date 2018/8/1
 */
@Slf4j
public class RequestLogFilter extends PostJsonRequestBodyCacheFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        log.debug("RequestURL\t{}{}",
                httpServletRequest.getRequestURL(),
                httpServletRequest.getQueryString() != null ? String.format("?%s", httpServletRequest.getQueryString()) : StringUtils.EMPTY
        );
        if (httpServletRequest instanceof BodyReaderHttpServletRequestWrapper) {
            ServletInputStream inputStream = httpServletRequest.getInputStream();
            String body = IOUtils.toString(inputStream, "UTF-8");
            inputStream.reset();
            log.debug("RequestBody\t{}", StringUtils.replaceAll(body, "( *\\n *|\\t)", ""));
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
