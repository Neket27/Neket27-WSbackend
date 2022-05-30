package cardemployee.example.wsapp;
import employeeCard.CreateCardsEmployeesByInfoFromFile;
import employeeCard.Employee;
import employeeCard.Post;
import model.InMemoryEmployeeCardService;
import model.InMemoryPostService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import service.CreateCardsEmployeesByInfoFromFileService;
import service.EmployeeCardService;
import service.PostService;

@SpringBootApplication
@Import({Employee.class, Post.class, CreateCardsEmployeesByInfoFromFile.class,
        InMemoryEmployeeCardService.class, InMemoryPostService.class})

public class WSappApplication {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(WSappApplication.class, args);

        final String PATH = args[0];

        if (PATH.isEmpty())
            throw new Exception("No PATH");

        EmployeeCardService employeeCardService = context.getBean(InMemoryEmployeeCardService.class);
        PostService postService = context.getBean(InMemoryPostService.class);
        postService.createPosts();
        CreateCardsEmployeesByInfoFromFileService createCardsEmployeesByInfoFromFile =
                context.getBean(CreateCardsEmployeesByInfoFromFile.class);

        createCardsEmployeesByInfoFromFile.readEmployeesFromJson(PATH);
        employeeCardService.print();

    }

}
