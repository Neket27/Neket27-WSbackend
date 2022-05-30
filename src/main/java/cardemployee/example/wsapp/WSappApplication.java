package cardemployee.example.wsapp;


import component.MyComponent;
import employeeCard.CreateCardsEmployeesByInfoFromFile;
import employeeCard.Employee;
import employeeCard.Post;
import frontend.Frontend;
import model.InMemoryEmployeeCardService;
import model.InMemoryPostService;

import myInterface.ServiceFrontend;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import org.springframework.context.annotation.Import;
import service.CreateCardsEmployeesByInfoFromFileService;
import service.EmployeeCardService;
import service.PostService;

@SpringBootApplication
@Import({MyComponent.class, Frontend.class,
         Employee.class, Post.class,CreateCardsEmployeesByInfoFromFile.class,InMemoryEmployeeCardService.class,InMemoryPostService.class})

public class WSappApplication {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(WSappApplication.class, args);

//
        MyComponent myComponent=context.getBean(MyComponent.class);
        myComponent.outMyComponent();
        //////////////////////////////////////////////////////////////////
//        final String PATH = args[0];
        String PATH ="C:\\Users\\nikit\\Desktop\\java\\Backend\\WSapp\\src\\main\\resources\\Employees.json";
        if (PATH.isEmpty())
            throw new Exception("No PATH");

        EmployeeCardService employeeCardService =  context.getBean(InMemoryEmployeeCardService.class);
//        PostService postService=context.getBean(InMemoryPostService.class);
        PostService postService = new InMemoryPostService();
//        EmployeeCardService employeeCardService = new InMemoryEmployeeCardService();

        postService.createPosts();
        CreateCardsEmployeesByInfoFromFileService createCardsEmployeesByInfoFromFile =
                new CreateCardsEmployeesByInfoFromFile(employeeCardService, postService);
        createCardsEmployeesByInfoFromFile.readEmployeesFromJson(PATH);
        employeeCardService.print();




    }

}


//
//@Component
//class Shop {
//
//    void print(){
//        System.out.println("HEY");
//    }
//
//}
//
//@RestController
//@Component
//class Seller {
//
//    @Autowired
//    private Shop shop;
//
//    void write(){
//        shop.print();
//    }
//}
