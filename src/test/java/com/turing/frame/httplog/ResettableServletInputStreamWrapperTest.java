package com.turing.frame.httplog;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * @author weizhiwen
 * @date 2018/10/22
 */
@Slf4j
public class ResettableServletInputStreamWrapperTest extends AbstractTest {
    @Test
    public void dasd() throws IOException {
        byte[] data = "test".getBytes("UTF-8");
        ResettableServletInputStreamWrapper wrapper = new ResettableServletInputStreamWrapper(data);

        byte[] result = new byte[0];

        byte[] bytes = new byte[1024];
        while (wrapper.read(bytes) > 0) {
            result = ArrayUtils.addAll(result, bytes);
        }

        for (int i = 0; i < data.length; i++) {
            Assert.assertEquals(data[i], result[i]);
        }
    }
}
