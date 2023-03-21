package by.prvsega.restservice.services;

import by.prvsega.restservice.dto.AuthRequest;
import by.prvsega.restservice.exceptions.PasswordAndUsernameIncorrectException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;

    public void checkAndAuthenticate(AuthRequest authRequest) {

        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(),
                        authRequest.getPassword());

        try {
            authenticationManager.authenticate(authInputToken);
        } catch (BadCredentialsException e) {
            throw new PasswordAndUsernameIncorrectException();
        }



    }


}
