
package mirea.enjoyers.BestHackBack.Services;

import mirea.enjoyers.BestHackBack.Models.Push;
import mirea.enjoyers.BestHackBack.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

@Service
public class SSEService {

    @Autowired
    private PushService pushService;
    private final HashMap<SseEmitter, String> emitters = new HashMap<>();
    @Autowired
    private UserRepository userRepository;


    public void addEmitter(SseEmitter emitter, String role) {
        emitters.put(emitter, role+ new Random().nextInt(1000000000));
    }

    public void removeEmitter(SseEmitter emitter) {
        emitters.remove(emitter);
    }

    public void sendPushNotification(Long pushId) {
        Push push = pushService.getPushById(pushId);
        if (push != null) {
            String payload = getString(push);
            for (SseEmitter emitter : emitters.keySet()) {
                try {
                    String emitterValue = emitters.get(emitter);
                    System.out.println(emitterValue + " " + push.getRoleDestination());
                    if (emitterValue != null &&
                            (emitterValue.contains(push.getRoleDestination())
                            || push.getRoleDestination().equals("all"))) {
                        emitter.send(payload);
                    }
                } catch (IOException e) {
                    removeEmitter(emitter);
                }
            }
        }
    }

    private static String getString(Push push) {
        return String.format
                ("{\"title\":\"%s\",\"description\":\"%s\"," +
                                "\"category\":\"%s\",\"image\":\"%s\",\"role_destination\":\"%s\"," +
                                "\"status\":\"%s\",\"datetime\":\"%s\",\"sender\":\"%s\"}",
                        push.getTitle(), push.getDescription(),
                        push.getCategory(), push.getImage(),
                        push.getRoleDestination(), push.getStatus(),
                        push.getDatetime(), push.getUser().getUsername());
    }

    public SseEmitter handleSse(String role) {
        SseEmitter emitter = new SseEmitter();
        System.out.println(userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getRoles());
        this.addEmitter(emitter, role);
        emitter.onCompletion(() -> this.removeEmitter(emitter));
        emitter.onTimeout(() -> this.removeEmitter(emitter));
        return emitter;
    }
}