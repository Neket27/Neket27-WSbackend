package whitesoftapp.whitesoftapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import whitesoftapp.whitesoftapp.model.Post;
import whitesoftapp.whitesoftapp.model.dtos.post.PostDto;
import whitesoftapp.whitesoftapp.repository.InMemoryPost;
import whitesoftapp.whitesoftapp.utils.mapper.PostMapper;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PostService {

    private final InMemoryPost inMemoryPost;
    private final PostMapper postMapper;

    public PostDto createPost(PostDto postDto) {
        Post post = postMapper.toPost(postDto);
        inMemoryPost.add(post);
        return postDto;
    }

    public Post getById(UUID id) throws Exception {
        Post post = inMemoryPost.get(id);
        if (post == null) {
            throw new Exception("No post with id = "+id);
        }
        return post;
    }

}
