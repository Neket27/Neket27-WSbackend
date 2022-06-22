package whitesoftapp.whitesoftapp.action.createEmployeesByInfoFromFile.parser;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import whitesoftapp.whitesoftapp.model.JobType;
import whitesoftapp.whitesoftapp.model.dtos.employee.CreateEmployeeDto;
import whitesoftapp.whitesoftapp.repository.InMemoryPost;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
@Primary
@Component
public class ParsInfoEmployeeFromTxt {

    private final Pattern employeesFilePattern = Pattern.compile(
                    "firstName: (?<firstName>.+)" +
                    "lastName: (?<lastName>.+)" +
                    "description: (?<description>.+|)" +
                    "characteristics: (?<characteristics>.+)" +
                    "postId: (?<postId>.+)");

    private final InMemoryPost inMemoryPost;

    public CreateEmployeeDto dataEmployee(String dataEmployee) throws Exception {
        Matcher m = employeesFilePattern.matcher(dataEmployee);
        if (!m.find())
            throw new Exception("File data is incorrect: " + dataEmployee);

        List<String> characteristics = (Arrays.stream(m.group("characteristics").split(", ")).sorted()).collect(Collectors.toList());
        if ((m.group("firstName").isEmpty()) || (m.group("lastName").isEmpty()) || characteristics.isEmpty() || inMemoryPost.get(UUID.fromString(m.group("postId"))) == null)
            throw new Exception("fill in the fields(the description may be empty)");

        return CreateEmployeeDto.builder()
                .firstName(m.group("firstName"))
                .lastName(m.group("firstName"))
                .description(m.group("description"))
                .characteristics(characteristics)
                .postDto(null)
                .contactsDto(null)
                .jobType(JobType.valueOf(m.group("jobType")))
                .build();
    }

}