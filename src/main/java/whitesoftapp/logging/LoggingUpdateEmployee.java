package whitesoftapp.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import whitesoftapp.model.Employee;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Aspect
@Component
public class LoggingUpdateEmployee {

    @Pointcut("execution(public * whitesoftapp.service.employee.EmployeeService.update())")
    private void callUpdateFields(){}

    @Before("callUpdateFields()")
    private void logUpdatedFields(JoinPoint joinPoint){
        List<Object> data=Arrays.stream(joinPoint.getArgs()).collect(Collectors.toList());
        Employee employee= (Employee) data.get(0);
        Employee updatedEmployee= (Employee) data.get(1);

        if (!employee.getFirstName().equals(updatedEmployee.getFirstName())) {
            log.info("First name: {} changed on {}", employee.getFirstName(), updatedEmployee.getFirstName());
        }
        if (!employee.getLastName().equals(updatedEmployee.getLastName())) {
            log.info("Last name: {} changed on {}", employee.getLastName(), updatedEmployee.getLastName());
        }
        if (!employee.getDescription().equals(updatedEmployee.getDescription())) {
            log.info("Description: {} changed on {}", employee.getDescription(), updatedEmployee.getDescription());
        }
        if (!employee.getCharacteristics().equals(updatedEmployee.getCharacteristics())) {
            log.info("Characteristics: {} changed on {}", employee.getCharacteristics(), updatedEmployee.getCharacteristics());
        }
        if (!employee.getContacts().equals(updatedEmployee.getContacts())) {
            log.info("Contacts: {} changed on {}", employee.getContacts(), updatedEmployee.getContacts());
        }
        if (!employee.getJobType().equals(updatedEmployee.getJobType())) {
            log.info("Job type: {} changed on {}", employee.getJobType(), updatedEmployee.getJobType());
        }
        if (!employee.getPost().equals(updatedEmployee.getPost())) {
            log.info("Post: {} changed on {}", employee.getPost(), updatedEmployee.getPost());
        }
    }
}
