//import action.CreateCardsEmployeesByInfoFromFile;
//
//import repository.InMemoryEmployeeCardService;
//import repository.InMemoryPostService;
//import service.EmployeeCardService;
//import service.CreateCardsEmployeesByInfoFromFileService;
//import service.PostService;
//
//public class MainApp {
//
//    public MainApp() {}
//
//    public static void main(String[] args) throws Exception {
//        final String PATH = args[0];
//        if (PATH.isEmpty())
//            throw new Exception("No PATH");
//
//
//        PostService postService = new InMemoryPostService();
//        EmployeeCardService employeeCardService = new InMemoryEmployeeCardService();
//
//        postService.createPosts();
//        CreateCardsEmployeesByInfoFromFileService createCardsEmployeesByInfoFromFile =
//                new CreateCardsEmployeesByInfoFromFile(employeeCardService, postService);
//        createCardsEmployeesByInfoFromFile.readEmployeesFromJson(PATH);
//        employeeCardService.print();
//
//    }
//
//}
