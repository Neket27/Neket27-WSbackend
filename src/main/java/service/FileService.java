package service;

import model.InMemoryEmployeeCardService;
import model.InMemoryPostService;
import org.json.simple.JSONArray;

public interface FileService {

    InMemoryPostService getPosts();

    InMemoryEmployeeCardService getEmployees();

    JSONArray readEmployeesFromJson(final String PATH);

}
