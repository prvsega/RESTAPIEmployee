package by.prvsega.restservice.services;

import by.prvsega.restservice.dto.EmployeeDTO;
import by.prvsega.restservice.models.Employee;
import by.prvsega.restservice.models.Roles;
import by.prvsega.restservice.repositories.EmployeeRepository;
import by.prvsega.restservice.repositories.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;


@Service
@Transactional(readOnly = true)
public class EmployeeService {
    private final RoleService roleService;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper, RoleService roleService) {
        this.employeeRepository = employeeRepository;
        this.roleService = roleService;
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findOne(int id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Transactional
    public Employee save(Employee employee) {
        addRoleUser(employee);
        return employeeRepository.save(employee);
    }

    @Transactional
    public void update(int id, Employee updateEmployee) {
        updateEmployee.setId(id);
        employeeRepository.save(updateEmployee);
    }

    @Transactional
    public void delete(int id) {
        employeeRepository.deleteById(id);
    }



    public Employee addRoleUser(Employee employee){
        Roles roles = roleService.getRolesOne(3);
        employee.setRolesSet(new HashSet<>(Collections.singletonList(roles)));
        return employee;
    }
}
