package whitesoftapp.whitesoftapp.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import whitesoftapp.whitesoftapp.model.Employee;
import whitesoftapp.whitesoftapp.model.dtos.EmployeeDto;
import java.util.HashMap;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class DtoUtilsEmployee {

    private final ObjectMapper objectMapper;

    public EmployeeDto convertToDto(Employee employee, EmployeeDto mapper) {
        return objectMapper.convertValue(employee, mapper.getClass());
    }

    public Employee convertToEntity(Employee employee, EmployeeDto mapper) {
        return objectMapper.convertValue(mapper,employee.getClass());
    }

    public HashMap<UUID,EmployeeDto> convertListToDto(HashMap<UUID,Employee> source, Class<EmployeeDto> mapper) {
        HashMap<UUID,EmployeeDto>employeeDto=new HashMap<>();
        source.entrySet().forEach(e->{
            employeeDto.put(e.getKey(), objectMapper.convertValue( e.getValue(), mapper));
        });
        return employeeDto;
    }

}
