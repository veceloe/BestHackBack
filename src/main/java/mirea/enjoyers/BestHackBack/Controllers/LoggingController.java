package mirea.enjoyers.BestHackBack.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LoggingController {

    @PostMapping("/log/set")
    public void setLogLevel() {
    }

    @PostMapping("/log/get")
    public void getLogLevel() {
    }

    @PostMapping("/log/save")
    public void saveLog() {
    }

    @PostMapping("/log/fromString")
    public void fromString() {
    }


}
