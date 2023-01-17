package by.prvsega.restservice.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RoleNameDTO {
    private int id;
    private String rolesName;
    
    private List<EmployeeNameDTO> employeesList = new ArrayList<>();

}
