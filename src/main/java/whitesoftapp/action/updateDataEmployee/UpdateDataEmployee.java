package whitesoftapp.action.updateDataEmployee;

import org.springframework.stereotype.Component;
import whitesoftapp.model.Employee;

@Component
public class UpdateDataEmployee implements UpdateData {

    @Override
    public void updateFields(Employee employee, Employee updatedEmployee) {

        if (!employee.getFirstName().equals(updatedEmployee.getFirstName())) {
            employee.setFirstName(updatedEmployee.getFirstName());
        }
        if (!employee.getLastName().equals(updatedEmployee.getLastName())) {
            employee.setLastName(updatedEmployee.getLastName());
        }
        if (!employee.getDescription().equals(updatedEmployee.getDescription())) {
            employee.setDescription(updatedEmployee.getDescription());
        }
        if (!employee.getCharacteristics().equals(updatedEmployee.getCharacteristics())) {
            employee.setCharacteristics(updatedEmployee.getCharacteristics());
        }
        if (!employee.getContacts().equals(updatedEmployee.getContacts())) {
            employee.setContacts(updatedEmployee.getContacts());
        }
        if (!employee.getJobType().equals(updatedEmployee.getJobType())) {
            employee.setJobType(updatedEmployee.getJobType());
        }
        if (!employee.getPost().equals(updatedEmployee.getPost())) {
            employee.setPost(updatedEmployee.getPost());
        }
    }

}
