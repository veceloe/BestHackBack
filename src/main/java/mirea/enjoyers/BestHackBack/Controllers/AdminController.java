package mirea.enjoyers.BestHackBack.Controllers;

import mirea.enjoyers.BestHackBack.DTO.UserDto;
import mirea.enjoyers.BestHackBack.Models.Push;
import mirea.enjoyers.BestHackBack.Models.User;
import mirea.enjoyers.BestHackBack.Services.Impls.PushServiceImpl;
import mirea.enjoyers.BestHackBack.Services.Impls.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {
    private final UserServiceImpl userService;
    private final PushServiceImpl pushService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.allUsers();
    }

    @PostMapping("/push/create")
    public String createPush(Push push) {
        pushService.savePush(push);
        return "redirect:/";
    }

    @PutMapping("/users")
    public ResponseEntity<?> updateUser(@RequestBody UserDto user) {
        userService.updateUser(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/push/delete/{id}")
    public String deletePush(@PathVariable Long id) {
        pushService.deletePush(id);
        return "redirect:/";
    }
}