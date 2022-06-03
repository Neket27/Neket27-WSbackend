package whitesoftapp.whitesoftapp.service;

import org.springframework.stereotype.Service;
import whitesoftapp.whitesoftapp.model.Post;

import java.util.UUID;

@Service
public interface PostService11 {

    void createPosts();

    Post createPost(UUID uuid, String name);

    Post get(UUID id);

    void add(Post post);
}
