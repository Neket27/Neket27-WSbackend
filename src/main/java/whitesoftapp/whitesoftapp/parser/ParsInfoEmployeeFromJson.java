package whitesoftapp.whitesoftapp.parser;

import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import whitesoftapp.whitesoftapp.controller.EmployeeController;
import whitesoftapp.whitesoftapp.model.Post;
import whitesoftapp.whitesoftapp.model.dtos.CreateEmployeeDto;
import whitesoftapp.whitesoftapp.repository.InMemoryPost;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class ParsInfoEmployeeFromJson implements Parser{

    private final InMemoryPost inMemoryPost;

    public CreateEmployeeDto dataEmployee(JSONObject employee) {
        List<String> characteristics = new ArrayList<>((JSONArray) employee.get("characteristics"));
        Post post = inMemoryPost.get(UUID.fromString((String) employee.get("postId")));
        return CreateEmployeeDto.builder()
                .firstName(employee.get("firstName").toString())
                .lastName(employee.get("lastName").toString())
                .description(employee.get("description").toString())
                .characteristics(characteristics)
                .post(post)
                .build();
    }
}
