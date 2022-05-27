import model.InMemoryFile;

public class MainApp {

    private static InMemoryFile inMemoryFile = new InMemoryFile();

    public MainApp() {}

    public static void main(String[] args) throws Exception {
        final String PATH = args[0];
        if (PATH.isEmpty())
            throw new Exception("No PATH");
        inMemoryFile.getPosts().createPosts();
        inMemoryFile.readEmployeesFromJson(PATH);
        inMemoryFile.getEmployees().printSortedByFirstAndLastName();
    }

}