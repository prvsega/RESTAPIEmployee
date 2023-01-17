package by.prvsega.restservice.models;

import by.prvsega.restservice.util.RolesName;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Roles")
@Data
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roles_id")
    private int id;

    @Column(name = "roles_name")
    @NotEmpty
    private String rolesName;

    @ManyToMany(mappedBy = "rolesSet")
    private List<Employee> employeesList;

    public Roles() {

    }


}
