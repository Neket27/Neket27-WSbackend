package service;

import model.InMemoryEmployeeCardService;
import model.InMemoryPostService;
import org.json.simple.JSONArray;

public interface CreateCardsEmployeesByInfoFromFileService {

    void createPosts();

    JSONArray readEmployeesFromJson(final String PATH);

    void readEmployeesFromFile(String PATH) throws Exception;

    void printSortedByFirstAndLastName();

    InMemoryPostService getPosts();

    InMemoryEmployeeCardService getEmployees();


}
