package by.prvsega.restservice.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import java.util.Set;

@Entity
@Table(name = "Employee")
@Getter
@Setter
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private int id;

    @Column(name = "first_name")
    @NotEmpty
    @Size(max=20)
    private String firstName; //20

    @Column(name = "last_name")
    @NotEmpty
    @Size(max=30)
    private String lastName; //30

    @Column(name = "email")
    @NotEmpty
    @Size(max=100)
    @Email
    private String email; //100

    @Column(name = "job_title")
    @NotEmpty
    @Size(max=100)
    private String jobTitle; //100

    @Column(name = "gender")
    @NotEmpty
    @Size(max=20)
    private String gender; //20

    @Column(name = "username")
    @NotEmpty
    private String username;

    @Column(name = "password")
    @NotEmpty
    private String password;


    @ManyToMany
    @JoinTable(
            name = "Employee_Roles",
            joinColumns = @JoinColumn(name = "er_employee_id"),
            inverseJoinColumns = @JoinColumn(name = "er_roles_id")
    )
    private Set<Role> rolesSet;
}
