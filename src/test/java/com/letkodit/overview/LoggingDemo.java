package com.letkodit.overview;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LoggingDemo {
    private static final Logger log = LogManager.getLogger(LoggingDemo.class.getName());

    public static void main(String[] args) {
        log.debug("ok");
        log.trace("ok");
        log.error("ok");
        log.info("ok");
        log.fatal("ok");
    }
}
