package mirea.enjoyers.BestHackBack.Controllers;

import mirea.enjoyers.BestHackBack.Models.Push;
import mirea.enjoyers.BestHackBack.Models.User;
import mirea.enjoyers.BestHackBack.Services.PushService;
import lombok.RequiredArgsConstructor;
import mirea.enjoyers.BestHackBack.Services.SSEService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://158.160.159.255")
public class PushController {
    private final PushService pushService;
    private final SSEService sseService;

    @GetMapping("/pushes")
    public List<Push> pushes(@RequestParam(name = "title", required = false) String title) {
        return pushService.listPushes(title);
    }

    @GetMapping("/push")
    public Push pushById(@RequestParam(name = "id") Long id) {
        return pushService.getPushById(id);
    }

    @PostMapping("/image/{id}")
    public ResponseEntity<Resource> uploadImage(@PathVariable Long id) {
        try {
            String base64Image = pushService.getPushById(id).getImage();
            String extension = base64Image.substring(base64Image.indexOf("/") + 1, base64Image.length() - 1);
            String fileName = UUID.randomUUID() + "." + extension;
            byte[] imageBytes = Base64.getDecoder().decode(base64Image.getBytes());
            File file = File.createTempFile(fileName, "." + extension);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(imageBytes);
            fos.close();
            Resource resource = new UrlResource(file.toURI());
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping(value = "/push", consumes = {"application/json"})
    public void savePush(@RequestBody Push push) {
        pushService.savePush(push);
        sseService.sendPushNotification(push.getId());
    }

    @DeleteMapping("/push")
    public void deletePush(@RequestParam(name = "id") Long id) {
        pushService.deletePush(id);
    }
}