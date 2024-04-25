package mirea.enjoyers.BestHackBack.Repositories;

import mirea.enjoyers.BestHackBack.Models.Push;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PushRepository extends JpaRepository<Push, Long> {
    List<Push> findByTitleContaining(String title);
    List<Push> findAllByRoleDestination(String role);
}
