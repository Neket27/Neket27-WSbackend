package whitesoftapp.whitesoftapp.service.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import whitesoftapp.whitesoftapp.controller.utils.mapper.post.PostMapper;
import whitesoftapp.whitesoftapp.model.Post;
import whitesoftapp.whitesoftapp.model.dtos.post.PostDto;
import whitesoftapp.whitesoftapp.repository.InMemoryPost;


import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PostService {

    private final InMemoryPost inMemoryPost;
    private final PostMapper postMapper;

    public PostDto createPost(PostDto postDto) {
        Post post = postMapper.toEntity(postDto);
        inMemoryPost.add(post);
        return postDto;
    }

    public PostDto getById(UUID id) throws Exception {
        Post post = inMemoryPost.get(id);
        if (post == null) {
            throw new Exception("No post with id = "+id);
        }
        return postMapper.toDto(post);
    }

}
