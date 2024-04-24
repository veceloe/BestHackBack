package mirea.enjoyers.BestHackBack.Repositories;

import mirea.enjoyers.BestHackBack.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}