package whitesoftapp.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import whitesoftapp.model.Employee;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Aspect
@Component
public class LoggingEmployee {

    @Pointcut("execution(public * whitesoftapp.controller.employee.EmployeeController.*(..))")
    private void callAtEmployeeController() { }

    @Pointcut("execution(public * whitesoftapp.action.updateDataEmployee.UpdateData.*(..))")
    private void callUpdateFields(){}

    @Before("callAtEmployeeController()")
    private void logParams(JoinPoint jp) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        List<String> args = Arrays.stream(jp.getArgs())
                                  .map(a -> a.toString())
                                  .collect(Collectors.toList());

        log.info("Class request: {}",jp.getSignature().getDeclaringTypeName());
        log.info("Request {} {}",jp.getSignature().getName(),"with params:");
        log.info("Id: {}", (args.size()!=0 ) ? args.get(0) : null);
        log.info("Request IP: {}", request.getRemoteAddr());
    }

    @AfterThrowing(pointcut = "callAtEmployeeController()", throwing = "e")
    private void logException(JoinPoint jp, Throwable e) {
        log.error("Exception in {}.{}() with message = {}", jp.getSignature().getDeclaringTypeName(),
                  jp.getSignature().getName(), e.getMessage() != null ? e.getMessage() : "NULL");
    }

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
