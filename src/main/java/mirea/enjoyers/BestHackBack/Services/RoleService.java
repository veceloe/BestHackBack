package mirea.enjoyers.BestHackBack.Services;

import mirea.enjoyers.BestHackBack.Models.Role;
import mirea.enjoyers.BestHackBack.Repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getUserRole() {
        return roleRepository.findByName("ROLE_USER");
    }

    public Role getAdminRole() {
        return roleRepository.findByName("ROLE_ADMIN");
    }

    public Role getEmployeeRole() {
        return roleRepository.findByName("ROLE_EMPLOYEE");
    }
}