package Service;

import EmployeeCard.Employee;
import java.util.UUID;

public interface EmployeeCardService {

   Employee get(UUID id);
   Employee get(String firstName, String lastName);
   void add(Employee employee);

}
