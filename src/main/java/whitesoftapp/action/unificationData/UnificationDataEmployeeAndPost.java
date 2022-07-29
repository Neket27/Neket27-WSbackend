package whitesoftapp.action.unificationData;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import whitesoftapp.arguments.CreateEmployeeArgument;
import whitesoftapp.arguments.UpdateEmployeeArgument;
import whitesoftapp.controller.utils.mapper.employee.EmployeeMapper;
import whitesoftapp.model.Employee;
import whitesoftapp.model.Post;
import whitesoftapp.service.post.PostService;

import javax.transaction.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class UnificationDataEmployeeAndPost implements UnificationData {

    private final PostService postService;
    private final EmployeeMapper employeeMapper;

    @Override
    public Employee unificationDataEmployeeWitchPost(CreateEmployeeArgument createEmployeeArgument) {
        Post post = postService.getById(createEmployeeArgument.getPostId());
        Employee employee = employeeMapper.toEntityFromUpdateArgument(createEmployeeArgument);
        employee.setPost(post);
        return employee;
    }

    @Override
    public Employee unificationDataEmployeeWitchPost(UpdateEmployeeArgument updateEmployeeArgument) {
        Post post = postService.getById(updateEmployeeArgument.getPostId());
        Employee employee = employeeMapper.toEntityFromUpdateArgument(updateEmployeeArgument);
        employee.setPost(post);
        return employee;
    }

}
