package mirea.enjoyers.BestHackBack.Services.Impls;

import mirea.enjoyers.BestHackBack.Models.Push;
import mirea.enjoyers.BestHackBack.Models.Role;
import mirea.enjoyers.BestHackBack.Repositories.PushRepository;
import mirea.enjoyers.BestHackBack.Repositories.UserRepository;
import mirea.enjoyers.BestHackBack.Services.PushService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Security;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PushServiceImpl implements PushService {

    private final PushRepository pushRepository;
    private final UserRepository userRepository;

    @Override
    public List<Push> listPushes(String title) {
        List<Push> pushes = new ArrayList<>();
        if (title != null) {
            pushes = pushRepository.findByTitleContaining(title);
        } else {

            List<Role> roles = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getRoles();
            if (roles.get(0).getName().equals("ROLE_ADMIN")) {
                pushes = pushRepository.findAll();
            }
            else for (Role role : roles) {
                pushes.addAll(pushRepository.findAllByRoleDestination(role.getName()));
            }
        }
        pushes.sort(Comparator.comparing(Push::getDatetime));
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