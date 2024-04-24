package mirea.enjoyers.BestHackBack.Controllers;

import mirea.enjoyers.BestHackBack.Models.Push;
import mirea.enjoyers.BestHackBack.Models.User;
import mirea.enjoyers.BestHackBack.Services.Impls.PushServiceImpl;
import mirea.enjoyers.BestHackBack.Services.Impls.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final UserServiceImpl userService;
    private final PushServiceImpl pushService;

    @GetMapping("/allUsers")
    public List<User> getAllUsers() {
        return userService.allUsers();
    }

    @PostMapping("/push/create")
    public String createPush(Push push) {
        pushService.savePush(push);
        return "redirect:/";
    }

    @PostMapping("/push/delete/{id}")
    public String deletePush(@PathVariable Long id) {
        pushService.deletePush(id);
        return "redirect:/";
    }
}