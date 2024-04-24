package mirea.enjoyers.BestHackBack.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationUserDto {
    private String email;
    private String password;
}