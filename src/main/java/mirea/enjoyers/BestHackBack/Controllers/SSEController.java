// WebhookController.java
package mirea.enjoyers.BestHackBack.Controllers;

import mirea.enjoyers.BestHackBack.Services.SSEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
public class SSEController {

    private final SSEService webhookService;

    @Autowired
    public SSEController(SSEService webhookService) {
        this.webhookService = webhookService;
    }

    @GetMapping("/push/get")
    public SseEmitter handleSse() {
        SseEmitter emitter = new SseEmitter();
        webhookService.addEmitter(emitter);
        emitter.onCompletion(() -> webhookService.removeEmitter(emitter));
        emitter.onTimeout(() -> webhookService.removeEmitter(emitter));
        return emitter;
    }

    @PostMapping("/push/send")
    public void sendPushNotification(@RequestParam Long pushId) {
        webhookService.sendPushNotification(pushId);
    }
}