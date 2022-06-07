package whitesoftapp.whitesoftapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import whitesoftapp.whitesoftapp.model.Post;
import whitesoftapp.whitesoftapp.model.dtos.PostDto;
import whitesoftapp.whitesoftapp.repository.InMemoryPost;
import whitesoftapp.whitesoftapp.utils.DtoUtilsPost;

@RequiredArgsConstructor
@Service
public class PostService {

    private final InMemoryPost inMemoryPost;
    private final DtoUtilsPost dtoUtilsPost;

    public PostDto createPost(PostDto createPostDto) {
        Post post= dtoUtilsPost.convertToEntity(new Post(),createPostDto);
        inMemoryPost.add(post);
        return dtoUtilsPost.convertToDto(post, new PostDto());
    }

}
