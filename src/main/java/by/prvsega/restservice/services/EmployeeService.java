package by.prvsega.restservice.services;

import by.prvsega.restservice.models.Employee;
import by.prvsega.restservice.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findOne(int id) {

        return employeeRepository.findById(id).orElse(null);
    }

    @Transactional
    public Employee save(Employee employee) {

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

}
