package by.prvsega.restservice.security;

import by.prvsega.restservice.exceptions.EmployeeNotFoundException;
import by.prvsega.restservice.models.Employee;
import by.prvsega.restservice.repositories.EmployeeRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeDetailsServiceImpl implements UserDetailsService {
    private final EmployeeRepository employeeRepository;

    public EmployeeDetailsServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserDetails user = (UserDetails) employeeRepository.findByUsername(username).orElseThrow(EmployeeNotFoundException::new);
//        return user;
        Optional<Employee> employee = employeeRepository.findByUsername(username);

        if (employee.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }

        return new EmployeeDetailsImpl(employee.get());
    }
}
