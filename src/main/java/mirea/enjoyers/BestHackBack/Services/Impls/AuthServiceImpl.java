package mirea.enjoyers.BestHackBack.Services.Impls;

import mirea.enjoyers.BestHackBack.DTO.JwtRequest;
import mirea.enjoyers.BestHackBack.DTO.JwtResponse;
import mirea.enjoyers.BestHackBack.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl {
    private final UserServiceImpl userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authRequest.getEmail(),
                    authRequest.getPassword()
            ));
        } catch (BadCredentialsException e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getEmail());
        String token = jwtTokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
