package whitesoftapp.service.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import whitesoftapp.arguments.CreatePostArgument;
import whitesoftapp.controller.utils.mapper.post.PostMapper;
import whitesoftapp.model.Post;
import whitesoftapp.model.dtos.post.CreatePostDto;
import whitesoftapp.model.dtos.post.PostDto;
import whitesoftapp.repository.InMemoryPost;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PostService {

    private final InMemoryPost inMemoryPost;
    private final PostMapper postMapper;

    public Post createPost(CreatePostArgument createPostArgument) {
        Post post = postMapper.toEntityFromCreatePostArgument(createPostArgument);
        inMemoryPost.add(post);
        return post;
    }

    public Post getById(UUID id) throws Exception {
        Post post = inMemoryPost.get(id);
        if (post == null) {
            throw new Exception("No post with id = "+id);
        }
        return post;
    }


}
