package by.prvsega.restservice.contollers;

import by.prvsega.restservice.dto.AuthRequest;
import by.prvsega.restservice.dto.AuthResponse;
import by.prvsega.restservice.dto.EmployeeDTO;
import by.prvsega.restservice.security.JWTUtil;
import by.prvsega.restservice.services.AuthService;
import by.prvsega.restservice.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final EmployeeService employeeService;
    private final JWTUtil jwtUtil;
    private final AuthService authService;

    @PostMapping("/registration")
    public ResponseEntity <EmployeeDTO> registration (@RequestBody @Valid EmployeeDTO employeeDTO){
        return ResponseEntity.ok(employeeService.save(employeeDTO));

    }

    @PostMapping("/generationtoken")
    public ResponseEntity <AuthResponse> performLogin(@RequestBody @Valid AuthRequest authRequest){
        authService.checkAndAuthenticate(authRequest);

        String token = jwtUtil.generateToken(authRequest.getUsername());
        return ResponseEntity.ok(new AuthResponse(token));
    }

}
