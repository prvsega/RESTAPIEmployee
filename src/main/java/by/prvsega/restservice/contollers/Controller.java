package by.prvsega.restservice.contollers;

import by.prvsega.restservice.dto.EmployeeDTO;
import by.prvsega.restservice.models.Employee;
import by.prvsega.restservice.services.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController // Controller + ResponseBody
@RequestMapping("/employee")
public class Controller {

    private final EmployeeService employeeService;
    private final ModelMapper modelMapper;

    @Autowired
    public Controller(EmployeeService employeeService, ModelMapper modelMapper) {
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
    }

    @GetMapping // all employees
    public List<EmployeeDTO> getEmployees(){

        return employeeService.findAll().stream().map(this::converterToDTO).collect(Collectors.toList());

    }


    @GetMapping("/{id}")
    public EmployeeDTO getEmployee(@PathVariable("id") int id){
        Employee employee = employeeService.findOne(id);
        return converterToDTO(employee);
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody @Valid EmployeeDTO employeeDTO){

        Employee employee = employeeService.save(converterToEmployee(employeeDTO));

        return ResponseEntity.ok(converterToDTO(employee));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmployeeDTO> deleteEmployee(@PathVariable ("id") int id){
        EmployeeDTO employeeDTO = getEmployee(id);

        employeeService.delete(id);

        return ResponseEntity.ok(employeeDTO);


    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable("id") int id, @RequestBody @Valid EmployeeDTO updateEmployeeDTO){
        Employee employee = converterToEmployee(updateEmployeeDTO);

        employeeService.update(id, employee);

        return ResponseEntity.ok(converterToDTO(employee));



    }





    private EmployeeDTO converterToDTO(Employee employee){

        return modelMapper.map(employee, EmployeeDTO.class);
    }

    private Employee converterToEmployee ( EmployeeDTO employeeDTO){

        return modelMapper.map(employeeDTO, Employee.class);
    }

}
