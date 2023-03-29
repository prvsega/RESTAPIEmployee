package by.prvsega.restservice.services;


import by.prvsega.restservice.dto.EmployeeDTO;
import by.prvsega.restservice.dto.PageResponseDTO;
import by.prvsega.restservice.mappers.EmployeeMapper;
import by.prvsega.restservice.models.Employee;
import by.prvsega.restservice.models.Role;
import by.prvsega.restservice.repositories.EmployeeRepository;
import by.prvsega.restservice.exceptions.EmployeeNotFoundException;
import by.prvsega.restservice.services.mail.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static java.util.Objects.*;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmployeeService {
    private final RoleService roleService;
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final MailService mailService;
    private final PasswordEncoder passwordEncoder;

    public List<EmployeeDTO> findAll() {
        List<Employee> employees = employeeRepository.findAll(Sort.by("id"));
        return employeeMapper.converterToListDTO(employees);
    }

    public PageResponseDTO<EmployeeDTO> findAllPageable(int offset, int limit) {
        Page<Employee> page = employeeRepository.findAll(PageRequest.of(offset, limit, Sort.by("id")));
        List<Employee> employees = employeeRepository.findAll(PageRequest.of(offset, limit, Sort.by("id"))).getContent();
        return new PageResponseDTO<>(page.getTotalPages(), page.getTotalElements(), employeeMapper.converterToListDTO(employees));

    }


    public EmployeeDTO findOne(Integer id) {
        if (isNull(id)) {
            throw new EmployeeNotFoundException();
        }
        return employeeMapper.converterToDTO(employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new));
    }

    @Transactional
    public EmployeeDTO save(EmployeeDTO employeeDTO) {
        Employee employee = employeeMapper.converterToEmployee(employeeDTO);
        employee.setPassword(passwordEncoder.encode(employeeDTO.getPassword()));
        addRoleUserForEmployee(employee);
        mailService.sendEmailAboutRegistration(employee.getEmail(), "You registered on my website");

        return employeeMapper.converterToDTO(employeeRepository.save(employee));
    }

    @Transactional
    public void update(Integer id, EmployeeDTO updateEmployeeDTO) {
        if (isNull(id)) {
            throw new EmployeeNotFoundException();
        }
        Employee updateEmployee = employeeMapper.converterToEmployee(updateEmployeeDTO);
        updateEmployee.setId(id);
        employeeRepository.save(updateEmployee);
    }


    @Transactional
    public void delete(Integer id) {
        if (isNull(id)) {
            throw new EmployeeNotFoundException();
        }
        employeeRepository.deleteById(id);
    }

    public void addRoleUserForEmployee(Employee employee) {
        Role role = roleService.getRolesOne(3);
        employee.setRolesSet(new HashSet<>(Collections.singletonList(role)));
    }

}
