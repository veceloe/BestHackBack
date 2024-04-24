package mirea.enjoyers.BestHackBack.Services.Impls;

import mirea.enjoyers.BestHackBack.Models.Push;
import mirea.enjoyers.BestHackBack.Repositories.PushRepository;
import mirea.enjoyers.BestHackBack.Repositories.UserRepository;
import mirea.enjoyers.BestHackBack.Services.PushService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Security;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PushServiceImpl implements PushService {

    private final PushRepository pushRepository;
    private final UserRepository userRepository;

    @Override
    public List<Push> listPushes(String title) {
        List<Push> pushes;
        if (title != null) {
            pushes = pushRepository.findByTitleContaining(title);
        } else {
            pushes = pushRepository.findAll();
        }
        return pushes;
    }

    @Override
    public Push getPushById(long id) {
        return pushRepository.findById(id).orElse(null);
    }

    @Override
    public void savePush(Push push) {
        // getPricipal() is a method of SecurityContextHolder.getContext().getAuthentication()
        push.setUser(userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
        pushRepository.save(push);
    }

    @Override
    public void deletePush(Long id) {
        pushRepository.deleteById(id);
    }

}