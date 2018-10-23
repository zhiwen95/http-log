package com.zhiwen95.frame.httplog.filter;

import com.zhiwen95.frame.httplog.BodyReaderHttpServletRequestWrapper;
import com.zhiwen95.frame.httplog.Constants;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.junit.Assert;
import org.junit.Test;

import javax.servlet.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class PostJsonRequestBodyCacheFilterTest {

    @Test
    public void doFilter() throws IOException, ServletException {
        PostJsonRequestBodyCacheFilter filter = new PostJsonRequestBodyCacheFilter();
        FilterChain chain = new FilterChain() {
            public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException, ServletException {
                Assert.assertTrue(servletRequest instanceof BodyReaderHttpServletRequestWrapper);
            }
        };
        filter.doFilter(getRequest(Constants.METHOD_POST, Constants.APPLICATION_JSON_VALUE), new Response(), chain);
    }

    @Test
    public void doFilter2() throws IOException, ServletException {
        PostJsonRequestBodyCacheFilter filter = new PostJsonRequestBodyCacheFilter();
        FilterChain chain = new FilterChain() {
            public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException, ServletException {
                Assert.assertFalse(servletRequest instanceof BodyReaderHttpServletRequestWrapper);
            }
        };
        filter.doFilter(getRequest(Constants.METHOD_GET, Constants.APPLICATION_JSON_VALUE), new Response(), chain);
    }

    @Test
    public void isPostJson() {
        PostJsonRequestBodyCacheFilter filter = new PostJsonRequestBodyCacheFilter();
        Request request = getRequest(Constants.METHOD_POST, Constants.APPLICATION_JSON_VALUE);
        boolean postJson = filter.isPostJson(request);
        Assert.assertTrue(postJson);
    }

    private Request getRequest(final String method, final String contentType) {
        return new Request() {
            @Override
            public String getMethod() {
                return method;
            }

            @Override
            public String getContentType() {
                return contentType;
            }

            public ServletInputStream getInputStream() throws IOException {
                return new ServletInputStream() {
                    ByteArrayInputStream stream = new ByteArrayInputStream("test".getBytes());

                    @Override
                    public boolean isFinished() {
                        return false;
                    }

                    @Override
                    public boolean isReady() {
                        return false;
                    }

                    @Override
                    public void setReadListener(ReadListener readListener) {

                    }

                    @Override
                    public int read() throws IOException {
                        return stream.read();
                    }
                };
            }
        };
    }

    @Test
    public void isPostJson2() {
        PostJsonRequestBodyCacheFilter filter = new PostJsonRequestBodyCacheFilter();
        Request request = getRequest(Constants.METHOD_GET, Constants.APPLICATION_JSON_VALUE);
        boolean postJson = filter.isPostJson(request);
        Assert.assertFalse(postJson);
    }

    @Test
    public void isPostJson3() {
        PostJsonRequestBodyCacheFilter filter = new PostJsonRequestBodyCacheFilter();
        Request request = getRequest(Constants.METHOD_POST, Constants.APPLICATION_FORM_URLENCODED_VALUE);
        boolean postJson = filter.isPostJson(request);
        Assert.assertFalse(postJson);
    }

    @Test
    public void isPostJson4() {
        PostJsonRequestBodyCacheFilter filter = new PostJsonRequestBodyCacheFilter();
        Request request = getRequest(Constants.METHOD_GET, Constants.APPLICATION_FORM_URLENCODED_VALUE);
        boolean postJson = filter.isPostJson(request);
        Assert.assertFalse(postJson);
    }
}