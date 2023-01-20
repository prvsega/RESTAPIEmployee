package by.prvsega.restservice.services;


import by.prvsega.restservice.dto.EmployeeDTO;
import by.prvsega.restservice.mappers.EmployeeMapper;
import by.prvsega.restservice.models.Employee;
import by.prvsega.restservice.models.Role;
import by.prvsega.restservice.repositories.EmployeeRepository;
import by.prvsega.restservice.exceptions.EmployeeNotFoundException;
import by.prvsega.restservice.services.mail.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
public class EmployeeService {
    private final RoleService roleService;
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final MailService mailService;


    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, RoleService roleService,
                           EmployeeMapper employeeMapper, MailService mailService) {
        this.employeeRepository = employeeRepository;
        this.roleService = roleService;
        this.employeeMapper = employeeMapper;
        this.mailService = mailService;
    }

    public List<EmployeeDTO> findAll() {

        return employeeRepository.findAll().stream().map(employeeMapper::converterToDTO).collect(Collectors.toList());
    }

    public EmployeeDTO findOne(int id) {

        return employeeMapper.converterToDTO(employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new));
    }

    @Transactional
    public EmployeeDTO save(EmployeeDTO employeeDTO) {
        Employee employee = employeeMapper.converterToEmployee(employeeDTO);
        addRoleUserForEmployee(employee);
        sendEmailAboutRegistration(employee);

        return employeeMapper.converterToDTO(employeeRepository.save(employee));
    }

    @Transactional
    public void update(int id, EmployeeDTO updateEmployeeDTO) {
        findOne(id); // Check employee exists
        Employee updateEmployee = employeeMapper.converterToEmployee(updateEmployeeDTO);
        updateEmployee.setId(id);
        employeeRepository.save(updateEmployee);

    }

    @Transactional
    public void delete(int id) {
        findOne(id); // Check employee exists
        employeeRepository.deleteById(id);

    }


    public Employee addRoleUserForEmployee(Employee employee) {
        Role role = roleService.getRolesOne(3);
        employee.setRolesSet(new HashSet<>(Collections.singletonList(role)));
        return employee;
    }

    public void sendEmailAboutRegistration(Employee employee) {
        String toAddress = employee.getEmail();
        String subject = "Created new Employee";
        String message = "You " + employee.getFirstName() + ", " + employee.getLastName() + " registered on my website";
        mailService.sendSimpleEmail(toAddress, subject, message);
    }

    public void sendEmailAllEmployeeToNight() {
        List<String> listEmail = employeeRepository.findAll().stream().map(Employee::getEmail).collect(Collectors.toList()); // get a list of employee email
        String subject = "Employee care department";
        String message = "It is late. You must go to bed, because You will go to work and will be tired tomorrow";
        listEmail.stream().forEach(email -> mailService.sendSimpleEmail(email, subject, message));
    }

}
