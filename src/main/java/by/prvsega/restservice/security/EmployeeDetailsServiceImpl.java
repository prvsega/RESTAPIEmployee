package by.prvsega.restservice.security;

import by.prvsega.restservice.models.Employee;
import by.prvsega.restservice.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import static java.util.Objects.*;

@Service
@AllArgsConstructor
public class EmployeeDetailsServiceImpl implements UserDetailsService {
    private final EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByUsername(username);
        if (!nonNull(employee)){
            throw new UsernameNotFoundException("User not found");
        }

        return EmployeeDetailsImpl.fromUser(employee);
    }


}
