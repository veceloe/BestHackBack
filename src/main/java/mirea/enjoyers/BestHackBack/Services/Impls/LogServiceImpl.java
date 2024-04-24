package mirea.enjoyers.BestHackBack.Services.Impls;

import lombok.RequiredArgsConstructor;
import mirea.enjoyers.BestHackBack.Models.Log;
import mirea.enjoyers.BestHackBack.Repositories.LogRepository;
import mirea.enjoyers.BestHackBack.Services.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.LoggingEvent;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;
    private static final Logger logger = LoggerFactory.getLogger(LogServiceImpl.class);

    @Override
    public void saveLog(Log log) {
        logRepository.save(log);
    }

    @Override
    public void fromString(String log) {
    }

    @Override
    public void fromString(String log, String type, String date, String time, String user, String ip, String browser, String os, String device, String location, String url, String method, String status, String response, String timeTaken, String error) {
        String message = "Type: " + type + "\n" +
                "Date: " + date + "\n" +
                "Time: " + time + "\n" +
                "User: " + user + "\n" +
                "IP: " + ip + "\n" +
                "Browser: " + browser + "\n" +
                "OS: " + os + "\n" +
                "Device: " + device + "\n" +
                "Location: " + location + "\n" +
                "URL: " + url + "\n" +
                "Method: " + method + "\n" +
                "Status: " + status + "\n" +
                "Response: " + response + "\n" +
                "Time taken: " + timeTaken + "\n" +
                "Error: " + error;
        Log logClient = new Log();
        logClient.setMessage(message);
        message = message.toUpperCase();
        if (message.contains("ERROR"))
            logClient.setLevel("ERROR");
        else if (message.contains("WARN"))
            logClient.setLevel("WARN");
        else if (message.contains("INFO"))
            logClient.setLevel("INFO");
        else if (message.contains("DEBUG"))
            logClient.setLevel("DEBUG");
        else if (message.contains("TRACE"))
            logClient.setLevel("TRACE");
        else
            logClient.setLevel("ERROR");
        logClient.setSession("client");
        logClient.setLogger("react-app");
        logRepository.save(logClient);
    }

    @Override
    public void fromJavaThrowable(Throwable throwable) {
        Log log = new Log();
        log.setLevel("ERROR");
        log.setMessage(throwable.getMessage());
        log.setSession("server");
        log.setLogger("spring-boot");
        logRepository.save(log);
    }

    @Override
    public void fromLoggingEvent(LoggingEvent loggingEvent) {
        Log log = new Log();
        log.setLevel(loggingEvent.getLevel().toString());
        log.setMessage(loggingEvent.getMessage());
        log.setSession("server");
        log.setLogger(loggingEvent.getLoggerName());
        logRepository.save(log);
    }

    @Override
    public void deleteLog(Long id) {
        logRepository.deleteById(id);
    }
}
