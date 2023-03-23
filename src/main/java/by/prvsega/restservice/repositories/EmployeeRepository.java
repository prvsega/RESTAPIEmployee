package by.prvsega.restservice.repositories;

import by.prvsega.restservice.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
        @Query("select e from Employee e left join fetch e.rolesSet where e.username = ?1")
        Employee findByUsername (String username);
}
