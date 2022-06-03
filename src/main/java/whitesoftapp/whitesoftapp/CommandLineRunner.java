package whitesoftapp.whitesoftapp;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import whitesoftapp.whitesoftapp.action.CreateCardsEmployeesByInfoFromFile;
import whitesoftapp.whitesoftapp.controller.EmployeeController;
import whitesoftapp.whitesoftapp.controller.PostController;
import whitesoftapp.whitesoftapp.model.Post;
import whitesoftapp.whitesoftapp.model.dtos.CreateEmployeeDto;
import whitesoftapp.whitesoftapp.model.dtos.CreatePostDto;
import whitesoftapp.whitesoftapp.model.dtos.UpdateEmployeeDto;
import whitesoftapp.whitesoftapp.repository.InMemoryEmployeeCard;
import whitesoftapp.whitesoftapp.repository.InMemoryPost;
import whitesoftapp.whitesoftapp.service.CreateCardsEmployeesByInfoFromFileService;
import whitesoftapp.whitesoftapp.service.EmployeeCardService;
import whitesoftapp.whitesoftapp.service.EmployeeService;
import whitesoftapp.whitesoftapp.service.PostService11;
import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner {

    private final ApplicationContext context;

    @Override
    public void run(String... args) throws Exception {
//        final String PATH = args[0];
        String PATH = "C:\\Users\\nikit\\Desktop\\java\\Backend\\Neket27-WSbackend\\src\\main\\resources\\Employees.json";

        if (PATH.isEmpty())
            throw new Exception("No PATH");

        EmployeeCardService employeeCardService = context.getBean(InMemoryEmployeeCard.class);
        PostService11 postService = context.getBean(InMemoryPost.class);
        postService.createPosts();
        CreateCardsEmployeesByInfoFromFileService createCardsEmployeesByInfoFromFile =
                context.getBean(CreateCardsEmployeesByInfoFromFile.class);
        createCardsEmployeesByInfoFromFile.readEmployeesFromJson(PATH);


        EmployeeController employeeController = context.getBean(EmployeeController.class);
        PostController postController = context.getBean(PostController.class);
        EmployeeService employeeService = context.getBean(EmployeeService.class);

        employeeController.create(new CreateEmployeeDto("1", "2", "3", "4", new ArrayList<>(Collections.singleton("blabla")), new Post(UUID.fromString("99999999-3bc9-43ef-ae96-02a680a557d0"), "this Post")));
        employeeController.update(UUID.fromString("762d15a5-3bc9-43ef-ae96-02a680a557d0"), new UpdateEmployeeDto("RRRR", "NNN", "MMM", new ArrayList<>(), new Post(UUID.fromString("762d15a5-3bc9-43ef-ae96-02a680a557d0"), "namePost")));
        System.out.println(employeeController.getById(UUID.fromString("762d15a5-3bc9-43ef-ae96-02a680a557d0")));
//        employeeCardService.print();
        postController.createPost(new CreatePostDto(UUID.fromString("11111111-6c27-4635-926d-894d76a81707"), "Post_11111"));
        System.out.println(postService.get(UUID.fromString("11111111-6c27-4635-926d-894d76a81707")));
    }
}
