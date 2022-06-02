package service;

import model.Post;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface PostService11 {

    void createPosts();

    Post createPost(UUID uuid, String name);

    Post get(UUID id);

}
