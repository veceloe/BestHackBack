package mirea.enjoyers.BestHackBack.Services.Impls;

import mirea.enjoyers.BestHackBack.Configurations.UserDetail;
import mirea.enjoyers.BestHackBack.DTO.UserDto;
import mirea.enjoyers.BestHackBack.Models.User;
import mirea.enjoyers.BestHackBack.Repositories.RoleRepository;
import mirea.enjoyers.BestHackBack.Repositories.UserRepository;
import mirea.enjoyers.BestHackBack.Services.RoleService;
import mirea.enjoyers.BestHackBack.Services.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    RoleService roleService;

//    @Autowired
//    private JavaMailSender mailSender;

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = (String) authentication.getPrincipal();
        return userRepository.findUserByEmail(email);
    }

    @Override
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(User user) {
        if (user.getEmail().contains("admin")){
            user.setRoles(List.of(roleService.getAdminRole()));
        } else {
            user.setRoles(List.of(roleService.getUserRole()));
        }
        user.setPassword(encoder.encode(user.getPassword()));
        String token = UUID.randomUUID().toString();
        user.setVerificationToken(token);
//        String subject = "Подтверждение регистрации";
//        String verificationUrl = "http://localhost:8080/verify?token=" + user.getVerificationToken();
//        String message = "Для завершения регистрации перейдите по ссылке: " + verificationUrl;
//        SimpleMailMessage email = new SimpleMailMessage();
//        email.setTo(user.getEmail());
//        email.setSubject(subject);
//        email.setText(message);
//
//        mailSender.send(email);

        userRepository.save(user);
    }

    @Override
    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    @Override
    public List<User> userGetList(Long idMin) {
        return em.createQuery("SELECT u FROM User u WHERE u.id > :paramId", User.class)
                .setParameter("paramId", idMin).getResultList();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new UserDetail(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void updateUser(UserDto updatedUser) {
        User user = userRepository.findById(updatedUser.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id: " + updatedUser.getId()));

        user.setRoles(updatedUser.getRole());

        userRepository.save(user);
    }
}
