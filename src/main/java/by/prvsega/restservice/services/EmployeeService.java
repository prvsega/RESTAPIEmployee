package by.prvsega.restservice.services;

import by.prvsega.restservice.dto.EmployeeDTO;
import by.prvsega.restservice.models.Employee;
import by.prvsega.restservice.models.Roles;
import by.prvsega.restservice.repositories.EmployeeRepository;
import by.prvsega.restservice.util.RolesName;
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

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findOne(int id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Transactional
    public Employee save(Employee employee) {
        addRolesEmployee(employee);
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


    public EmployeeDTO converterToDTO(Employee employee){
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    public Employee converterToEmployee (EmployeeDTO employeeDTO){
        return modelMapper.map(employeeDTO, Employee.class);
    }

    public Employee addRolesEmployee(Employee employee){
        Roles roles = new Roles(RolesName.USER);

        employee.setRolesSet(new HashSet<>(Collections.singletonList(roles)));

        return employee;

    }
}
