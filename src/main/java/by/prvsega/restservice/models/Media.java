package by.prvsega.restservice.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "Files")
@Getter
@Setter
@NoArgsConstructor
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "files_id")
    private int id;

    @Column(name = "files_path")
    @NotEmpty
    private String path;

    @ManyToMany(mappedBy = "mediaList", fetch = FetchType.LAZY)
    private List<Employee> employeesList;

}
