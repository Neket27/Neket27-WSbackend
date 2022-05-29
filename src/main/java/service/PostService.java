package service;

import employeeCard.Post;
import java.util.UUID;

public interface PostService {

    void createPosts();

    Post createPost(UUID uuid, String name);

    Post get(UUID id);

}
