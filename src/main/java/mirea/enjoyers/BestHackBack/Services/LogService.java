package mirea.enjoyers.BestHackBack.Services;

import mirea.enjoyers.BestHackBack.Models.Log;
import org.slf4j.event.LoggingEvent;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface LogService {
    void saveLog(Log log);
    void fromString(String log);
    void fromString(String log, String type, String date, String time, String user, String ip, String browser, String os, String device, String location, String url, String method, String status, String response, String timeTaken, String error);
    void fromJavaThrowable(Throwable throwable);
    void fromLoggingEvent(LoggingEvent loggingEvent);
    void deleteLog(Long id);
}