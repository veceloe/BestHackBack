package mirea.enjoyers.BestHackBack.DTO;

import lombok.Data;

@Data
public class JwtRequest {
    private String email;
    private String password;
}
