package whitesoftapp.whitesoftapp.service;

import org.json.simple.JSONArray;
import org.springframework.stereotype.Service;
import whitesoftapp.whitesoftapp.repository.InMemoryEmployeeCard;
import whitesoftapp.whitesoftapp.repository.InMemoryPost;

@Service
public interface CreateCardsEmployeesByInfoFromFileService {

    void createPosts();

    JSONArray readEmployeesFromJson(final String PATH);

    void readEmployeesFromFile(String PATH) throws Exception;

    void printSortedByFirstAndLastName();

    InMemoryPost getPosts();

    InMemoryEmployeeCard getEmployees();


}
