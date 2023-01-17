package by.prvsega.restservice.contollers;

import by.prvsega.restservice.dto.EmployeeDTO;
import by.prvsega.restservice.mappers.EmployeeMapper;
import by.prvsega.restservice.models.Employee;
import by.prvsega.restservice.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController // Controller + ResponseBody
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeController(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @GetMapping // all employees
    public List<EmployeeDTO> getEmployees(){
        return employeeService.findAll().stream().map(employeeMapper::converterToDTO).collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    public EmployeeDTO getEmployee(@PathVariable("id") int id){
        Employee employee = employeeService.findOne(id);
        return employeeMapper.converterToDTO(employee);
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody @Valid EmployeeDTO employeeDTO){
        Employee employee = employeeService.save(employeeMapper.converterToEmployee(employeeDTO));
        return ResponseEntity.ok(employeeMapper.converterToDTO(employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmployeeDTO> deleteEmployee(@PathVariable ("id") int id){
        EmployeeDTO employeeDTO = getEmployee(id);
        employeeService.delete(id);
        return ResponseEntity.ok(employeeDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable("id") int id, @RequestBody @Valid EmployeeDTO updateEmployeeDTO){
        Employee employee = employeeMapper.converterToEmployee(updateEmployeeDTO);
        employeeService.update(id, employee);
        return ResponseEntity.ok(employeeMapper.converterToDTO(employee));
    }
}