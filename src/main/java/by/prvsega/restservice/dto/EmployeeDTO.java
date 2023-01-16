package by.prvsega.restservice.dto;

import by.prvsega.restservice.models.Roles;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Data
public class EmployeeDTO {

    private int id;

    @NotEmpty
    @Size(max=20)
    private String firstName; //20

    @NotEmpty
    @Size(max=30)
    private String lastName; //30

    @NotEmpty
    @Size(max=100)
    @Email
    private String email; //100

    @NotEmpty
    @Size(max=100)
    private String jobTitle; //100

    @NotEmpty
    @Size(max=20)
    private String gender; //20

}
