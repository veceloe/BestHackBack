package mirea.enjoyers.BestHackBack.Configurations;

import org.springframework.boot.logging.LoggingSystem;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggingSystemConfig {

    @Bean
    public LoggingSystem loggingSystem() {
        return LoggingSystem.get(ClassLoader.getSystemClassLoader());
    }
}