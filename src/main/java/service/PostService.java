package service;

import employeeCard.Post;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface PostService {

    void createPosts();

    Post createPost(UUID uuid, String name);

    Post get(UUID id);

}
