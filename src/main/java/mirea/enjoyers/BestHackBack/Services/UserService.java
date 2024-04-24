package mirea.enjoyers.BestHackBack.Services;

import mirea.enjoyers.BestHackBack.Models.User;

import java.util.List;

public interface UserService {
    User getCurrentUser();
    List<User> allUsers();
    void saveUser(User user);
    boolean deleteUser(Long userId);
    List<User> userGetList(Long idMin);
    User findByEmail(String email);
}
