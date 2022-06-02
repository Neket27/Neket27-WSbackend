package cardemployee.wsapp;

import action.CreateCardsEmployeesByInfoFromFile;
import controller.EmployeeController;
import controller.PostController;
import model.Employee;
import model.Post;
import model.dtos.CreateEmployeeDto;
import model.dtos.CreatePostDto;
import repository.InMemoryEmployeeCard;
import repository.InMemoryPost;
import service.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.UUID;

@SpringBootApplication()
@Import({Employee.class, Post.class, CreateCardsEmployeesByInfoFromFile.class,
        InMemoryEmployeeCard.class, InMemoryPost.class})

public class WSappApplication {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(WSappApplication.class, args);

//        final String PATH = args[0];
            String PATH="C:\\Users\\nikit\\Desktop\\java\\Backend\\Neket27-WSbackend\\src\\main\\resources\\Employees.json";

        if (PATH.isEmpty())
            throw new Exception("No PATH");

        EmployeeCardService employeeCardService = context.getBean(InMemoryEmployeeCard.class);
        PostService11 postService = context.getBean(InMemoryPost.class);
        postService.createPosts();
        CreateCardsEmployeesByInfoFromFileService createCardsEmployeesByInfoFromFile =
                context.getBean(CreateCardsEmployeesByInfoFromFile.class);

        createCardsEmployeesByInfoFromFile.readEmployeesFromJson(PATH);
        employeeCardService.print();
////////////////////////////////////////////////////////////////

        EmployeeController employeeController=new EmployeeController(new EmployeeService());
        System.out.println(employeeController.create(new CreateEmployeeDto("1","2","3","4",new ArrayList<>(),new Post())));
       Post post =new Post(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"),"PostName");
       PostController postController =new PostController(new PostService());
       System.out.println(postController.createPost(new CreatePostDto(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"),"PostName")));

    }

}
