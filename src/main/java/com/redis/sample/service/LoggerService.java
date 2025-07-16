package com.redis.sample.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

public class LoggerService {

    @Value("${nLogLevel}")
//    int logLevel;
    int globalLogLevel;

    // Custom levels: 0 (none) to 7 (all)
//    public static final int NONE = 0;
    public static final int ERROR = 1;
    public static final int INFO = 2;
    public static final int DEBUG = 3;
    public static final int TRACE = 4;
    public static final int PERFORMANCE = 5;
    public static final int TIME = 6;
    public static final int TEMP = 7;

//    private static int globalLogLevel = logLevel;

    private final Logger logger;

    public LoggerService(Class<?> clazz) {
        this.logger = LogManager.getLogger(clazz);
    }

    public void logError(String message) {
        if (globalLogLevel >= ERROR) {
            logger.error(message);
        }
    }

    public void logInfo(String message) {
        if (globalLogLevel >= INFO) {
            logger.info(message);
        }
    }

    public void logDebug(String message) {
        if (globalLogLevel >= DEBUG) {
            logger.debug(message);
        }
    }

    public void logTrace(String message) {
        if (globalLogLevel >= TRACE) {
            logger.trace(message);
        }
    }

    public void logPerformance(String message) {
        if (globalLogLevel >= PERFORMANCE) {
            logger.info("[PERF] " + message);
        }
    }

    public void logTime(String message) {
        if (globalLogLevel >= TIME) {
            logger.info("[TIME] " + message);
        }
    }

    public void logTemp(String message) {
        if (globalLogLevel >= TEMP) {
            logger.info("[TEMP] " + message);
        }
    }
}

