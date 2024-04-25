// WebhookController.java
package mirea.enjoyers.BestHackBack.Controllers;

import mirea.enjoyers.BestHackBack.Models.User;
import mirea.enjoyers.BestHackBack.Services.SSEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Objects;

@RestController
@CrossOrigin(origins = "http://158.160.159.255")
public class SSEController {

    private final SSEService webhookService;

    @Autowired
    public SSEController(SSEService webhookService) {
        this.webhookService = webhookService;
    }

    @GetMapping("/push/get/{role}")
    public SseEmitter handleSse(@PathVariable String role) {
        return webhookService.handleSse(role);
    }

    @PostMapping("/push/send")
    public void sendPushNotification(@RequestParam Long pushId) {
        webhookService.sendPushNotification(pushId);
    }
}