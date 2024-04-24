package mirea.enjoyers.BestHackBack.Controllers;

import mirea.enjoyers.BestHackBack.Services.WebhookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebhookController {

    private final WebhookService webhookService;

    @Autowired
    public WebhookController(WebhookService webhookService) {
        this.webhookService = webhookService;
    }

    @PostMapping("/webhook")
    public void sendPushNotification(@RequestParam String webhookUrl, @RequestParam Long pushId) {
        webhookService.sendPushNotification(webhookUrl, pushId);
    }
}