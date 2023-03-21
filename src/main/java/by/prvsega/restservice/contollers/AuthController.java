package by.prvsega.restservice.contollers;

import by.prvsega.restservice.dto.AuthRequestDTO;
import by.prvsega.restservice.dto.AuthResponseDTO;
import by.prvsega.restservice.dto.EmployeeDTO;
import by.prvsega.restservice.security.JWTUtil;
import by.prvsega.restservice.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final EmployeeService employeeService;
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/registration")
    public ResponseEntity <AuthResponseDTO> registration (@RequestBody @Valid EmployeeDTO employeeDTO){
        employeeService.save(employeeDTO);
        String token = jwtUtil.generateToken(employeeDTO.getUsername());
        return ResponseEntity.ok(new AuthResponseDTO(token));

    }
    @PostMapping("/generationtoken")
    public ResponseEntity <AuthResponseDTO> performLogin(@RequestBody @Valid AuthRequestDTO authRequestDTO){
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword());

    try{
    authenticationManager.authenticate(authenticationToken);
    } catch (BadCredentialsException e){
        return ResponseEntity.badRequest().body(new AuthResponseDTO("Incorrect username or/and password"));
    }

     String token = jwtUtil.generateToken(authRequestDTO.getUsername());

    return ResponseEntity.ok(new AuthResponseDTO(token));

    }


}
