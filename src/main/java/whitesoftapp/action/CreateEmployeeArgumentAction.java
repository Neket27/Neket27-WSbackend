package whitesoftapp.action;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import whitesoftapp.arguments.CreateEmployeeArgument;
import whitesoftapp.controller.post.PostController;
import whitesoftapp.controller.utils.mapper.employee.EmployeeMapper;
import whitesoftapp.controller.utils.mapper.post.PostMapper;
import whitesoftapp.model.Employee;
import whitesoftapp.model.Post;
import whitesoftapp.model.dtos.employee.CreateEmployeeDto;
import whitesoftapp.model.dtos.post.PostDto;
import whitesoftapp.service.employee.EmployeeService;
import whitesoftapp.service.post.PostService;

import javax.validation.Valid;

@RequiredArgsConstructor
@Component
public class CreateEmployeeArgumentAction {

    private final EmployeeMapper employeeMapper;
    private final PostMapper postMapper;
    private final PostService postService;

    public CreateEmployeeArgument create(@Valid CreateEmployeeDto createEmployeeDto) {
        CreateEmployeeArgument createEmployeeArgument=employeeMapper.toArgumentFromCreateEmployeeDto(createEmployeeDto);
        Post post=postService.getById(createEmployeeDto.getPostId());
        createEmployeeArgument.setPost(postMapper.toDto(post));
        return createEmployeeArgument;
    }
}
