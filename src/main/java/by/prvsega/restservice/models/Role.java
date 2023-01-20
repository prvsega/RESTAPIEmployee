package by.prvsega.restservice.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "Roles")
@Getter
@Setter
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roles_id")
    private int id;

    @Column(name = "roles_name")
    @NotEmpty
    private String rolesName;

    @ManyToMany(mappedBy = "rolesSet")
    private List<Employee> employeesList;



}
