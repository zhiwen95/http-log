package com.turing.frame.httplog.filter;

import com.turing.frame.httplog.Constants;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import javax.servlet.*;
import java.io.IOException;
import java.util.UUID;

/**
 * 添加请求ID
 *
 * @author weizhiwen
 * @date 2018/8/1
 */
@Slf4j
public class RequestIdFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String requestId = UUID.randomUUID().toString().replace("-", "");
        log.debug("bind request id {}", requestId);
        MDC.put(Constants.REQUEST_ID, requestId);
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            log.debug("clear request id");
            MDC.clear();
        }
    }

    public void destroy() {

    }
}
