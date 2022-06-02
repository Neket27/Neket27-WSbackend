package service;

import repository.InMemoryPost;
import repository.InMemoryEmployeeCard;
import org.json.simple.JSONArray;
import org.springframework.stereotype.Service;

@Service
public interface CreateCardsEmployeesByInfoFromFileService {

    void createPosts();

    JSONArray readEmployeesFromJson(final String PATH);

    void readEmployeesFromFile(String PATH) throws Exception;

    void printSortedByFirstAndLastName();

    InMemoryPost getPosts();

    InMemoryEmployeeCard getEmployees();


}
