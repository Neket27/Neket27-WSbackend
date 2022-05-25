package model;

import employeeCard.Post;
import service.PostService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Data
@RequiredArgsConstructor
public class InMemoryPostService implements PostService {
    private Map<UUID, Post> posts = new HashMap<>();

    @Override
    public Post createPost(UUID uuid, String name) {
        return posts.put(uuid, new Post(uuid, name));
    }

    @Override
    public void createPosts() {
        createPost(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"), "position at work_1");
        createPost(UUID.fromString("762d15a5-3bc9-43ef-ae96-02a680a557d0"), "position at work_2");
        createPost(UUID.fromString("606b99c0-b621-4f50-b0b6-58ed19ce6be1"), "position at work_3");
        createPost(UUID.fromString("762d15a5-3bc9-43ef-ae96-02a680a557d9"), "position at work_4");
    }

    @Override
    public Post get(UUID id) {
        return posts.get(id);
    }

}
