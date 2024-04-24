package mirea.enjoyers.BestHackBack.Services;

import mirea.enjoyers.BestHackBack.Models.Push;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebhookService {

    private final PushService pushService;
    private final RestTemplate restTemplate;

    public WebhookService(PushService pushService, RestTemplate restTemplate) {
        this.pushService = pushService;
        this.restTemplate = restTemplate;
    }

    public void sendPushNotification(String webhookUrl, Long pushId) {
        Push push = pushService.getPushById(pushId);
        if (push != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            String payload = String.format("{\"title\": \"%s\", \"body\": \"%s\"}", push.getTitle(), push.getDescription());
            HttpEntity<String> request = new HttpEntity<>(payload, headers);

            ResponseEntity<String> response = restTemplate.exchange(webhookUrl, HttpMethod.POST, request, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                System.out.println("Push notification sent successfully");
            } else {
                System.out.println("Failed to send push notification");
            }
        }
    }
}