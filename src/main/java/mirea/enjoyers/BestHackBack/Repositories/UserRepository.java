package mirea.enjoyers.BestHackBack.Repositories;

import mirea.enjoyers.BestHackBack.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);
    User findByEmail(String email);
}
