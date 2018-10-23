package com.zhiwen95.frame.httplog;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Bete数据流
 *
 * @author weizhiwen
 * @date 2018/10/22
 */
public class ResettableServletInputStreamWrapper extends ServletInputStream {
    private final ByteArrayInputStream input;

    public ResettableServletInputStreamWrapper(byte[] data) {
        this.input = new ByteArrayInputStream(data);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setReadListener(ReadListener listener) {
    }

    @Override
    public int read() throws IOException {
        return this.input.read();
    }

    @Override
    public synchronized void reset() throws IOException {
        this.input.reset();
    }
}
