package mirea.enjoyers.BestHackBack.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import mirea.enjoyers.BestHackBack.Models.Role;

import java.util.List;

@Data
@AllArgsConstructor
public class UserDto {
    private Long id;
    private List<Role> role;
}
