package by.prvsega.restservice.dto;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
public class EmployeeNameDTO {

    private int id;

   private String firstName; //20

   private String lastName; //30

   private String email; //100

   private String jobTitle; //100

   private String gender; //20

}
