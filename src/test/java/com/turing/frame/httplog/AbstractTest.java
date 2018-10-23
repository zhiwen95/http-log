package com.turing.frame.httplog;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

/**
 * @author weizhiwen
 * @date 2018/10/22
 */
@Slf4j
public abstract class AbstractTest {
    static {
        log.info("config logback");
        InputStream inputStream = AbstractTest.class.getResourceAsStream("/com/turing/frame/httplog/logback/default.xml");

        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        lc.reset();
        JoranConfigurator configurator = new JoranConfigurator();
        configurator.setContext(lc);
        try {
            configurator.doConfigure(inputStream);
        } catch (JoranException e) {
            e.printStackTrace(System.err);
        }
        log.info("run tests");
    }
}
