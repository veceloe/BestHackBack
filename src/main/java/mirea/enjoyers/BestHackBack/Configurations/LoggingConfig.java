// LoggingConfig.java
package mirea.enjoyers.BestHackBack.Configurations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggingConfig {
    private static final Logger logger = LoggerFactory.getLogger(LoggingConfig.class);
    private final LoggingSystem loggingSystem;

    public LoggingConfig(LoggingSystem loggingSystem) {
        this.loggingSystem = loggingSystem;
    }

    public void setLogLevel(String loggerName, String level) {
        LogLevel logLevel = LogLevel.valueOf(level);
        loggingSystem.setLogLevel(loggerName, logLevel);
        logger.info("Setting log level of {} to {}", loggerName, level);
    }
}