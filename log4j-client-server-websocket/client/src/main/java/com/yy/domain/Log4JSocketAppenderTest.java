package com.yy.domain;

/**
 * Created by yanyong on 2020/6/3.
 */

import org.apache.log4j.Logger;

import java.io.IOException;

public class Log4JSocketAppenderTest {

    private static final Logger logger = Logger.getLogger(Log4JSocketAppenderTest.class);

    public static void main(String[] args) throws IOException {
        int i = 0;
        while(true){
            i += 1;
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info(i + " bar logger info");
        }
    }
}
