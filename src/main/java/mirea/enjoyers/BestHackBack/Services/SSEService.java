
package mirea.enjoyers.BestHackBack.Services;

import mirea.enjoyers.BestHackBack.Models.Push;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SSEService {

    private final PushService pushService;
    private final List<SseEmitter> emitters = new ArrayList<>();

    public SSEService(PushService pushService) {
        this.pushService = pushService;
    }

    public void addEmitter(SseEmitter emitter) {
        emitters.add(emitter);
    }

    public void removeEmitter(SseEmitter emitter) {
        emitters.remove(emitter);
    }

    public void sendPushNotification(Long pushId) {
        Push push = pushService.getPushById(pushId);
        if (push != null) {
            String payload = String.format("{\"title\": \"%s\", \"body\": \"%s\"}", push.getTitle(), push.getDescription());

            for (SseEmitter emitter : emitters) {
                try {
                    emitter.send(payload);
                } catch (IOException e) {
                    removeEmitter(emitter);
                }
            }
        }
    }
}