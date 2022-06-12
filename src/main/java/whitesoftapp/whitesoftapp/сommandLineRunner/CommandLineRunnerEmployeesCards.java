package whitesoftapp.whitesoftapp.сommandLineRunner;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import whitesoftapp.whitesoftapp.action.createEmployeesByInfoFromFile.ReadEmployeesByInfoFromFile;
import whitesoftapp.whitesoftapp.controller.EmployeeController;
import whitesoftapp.whitesoftapp.controller.PostController;
import whitesoftapp.whitesoftapp.repository.InMemoryEmployeeCard;
import whitesoftapp.whitesoftapp.repository.InMemoryPost;
import whitesoftapp.whitesoftapp.service.EmployeeService;
import whitesoftapp.whitesoftapp.service.PostService;

@RequiredArgsConstructor
@Component
public class CommandLineRunnerEmployeesCards implements org.springframework.boot.CommandLineRunner {

    private final EmployeeService employeeService;
    private final PostService postService;
    private final InMemoryPost inMemoryPost;
    private final InMemoryEmployeeCard inMemoryEmployeeCard;
    private final ReadEmployeesByInfoFromFile readInfoAboutEmployeesFromFile;
    private final EmployeeController employeeController;
    private final PostController postController;


    @Override
    public void run(String... args) throws Exception {
//        final String PATH = args[0];
        String PATH = "C:\\Users\\nikit\\Desktop\\java\\Backend\\Neket27-WSbackend\\src\\main\\resources\\Employees.json";

        if (PATH.isEmpty())
            throw new Exception("No PATH");


        inMemoryPost.createPosts();
        readInfoAboutEmployeesFromFile.readEmployeesFromFile(PATH);
////        String PATH1= "C:\\Users\\nikit\\Desktop\\info.txt";
////        readInfoAboutEmployeesTxt.readEmployeesFromFile(PATH1);
//
//
//        employeeController.create(UUID.fromString("55555111-6c27-4635-926d-894d76a81707"),new EmployeeDto(UUID.fromString("55555111-6c27-4635-926d-894d76a81707"),"1", "2", "3", new ArrayList<>(Collections.singleton("blabla")), new Post(UUID.fromString("99999999-3bc9-43ef-ae96-02a680a557d0"), "this Post")));
//        System.out.println(employeeController.getById(UUID.fromString("55555111-6c27-4635-926d-894d76a81707")));
//        employeeController.update(UUID.fromString("55555111-6c27-4635-926d-894d76a81707"),new EmployeeDto(UUID.fromString("55555111-6c27-4635-926d-894d76a81707"),"z","z","z",new ArrayList<>(),new Post()));
//        System.out.println("with update= "+employeeController.getById(UUID.fromString("55555111-6c27-4635-926d-894d76a81707")));
////        employeeController.remove(UUID.fromString("55555111-6c27-4635-926d-894d76a81707"));
////        System.out.println("with update= "+employeeController.getById(UUID.fromString("55555111-6c27-4635-926d-894d76a81707")));
//
//       System.out.println("getID="+employeeController.getById(UUID.fromString("762d15a5-3bc9-43ef-ae96-02a680a557d0")));
//        employeeController.update(UUID.fromString("762d15a5-3bc9-43ef-ae96-02a680a557d0"), new EmployeeDto(UUID.fromString("762d15a5-3bc9-43ef-ae96-02a680a557d0"), "RRRR", "NNN", "MMM", new ArrayList<>(), new Post(UUID.fromString("762d15a5-3bc9-43ef-ae96-02a680a557d0"), "namePost")));
////        System.out.println(employeeController.getById(UUID.fromString("762d15a5-3bc9-43ef-ae96-02a680a557d0")));
////        employeeCardService.print();
//        postController.createPost(new PostDto(UUID.fromString("11111111-6c27-4635-926d-894d76a81707"), "Post_11111"));
//        System.out.println(inMemoryPost.get(UUID.fromString("11111111-6c27-4635-926d-894d76a81707")));
//
////        System.out.println("list= " + employeeController.getList());
//
//
//
//        employeeController.remove(UUID.fromString("762d15a5-3bc9-43ef-ae96-02a680a557d9"));
//        System.out.println(employeeController.getById(UUID.fromString("762d15a5-3bc9-43ef-ae96-02a680a557d9")));
        inMemoryEmployeeCard.printSortedByFirstAndLastName();

    }
}
