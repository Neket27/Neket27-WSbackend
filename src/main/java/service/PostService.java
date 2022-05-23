package service;

import employeeCard.Post;
import java.util.UUID;

public interface PostService {

    void createPost();
    Post createPost(UUID uuid, String name);

    }
