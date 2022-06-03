package whitesoftapp.whitesoftapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
import whitesoftapp.whitesoftapp.model.Employee;

import java.util.UUID;

@Repository
@NoRepositoryBean
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {



}
