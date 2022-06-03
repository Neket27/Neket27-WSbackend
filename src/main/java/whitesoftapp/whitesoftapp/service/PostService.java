package whitesoftapp.whitesoftapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import whitesoftapp.whitesoftapp.model.Post;
import whitesoftapp.whitesoftapp.model.dtos.CreatePostDto;
import whitesoftapp.whitesoftapp.model.dtos.DtoEntity;
import whitesoftapp.whitesoftapp.repository.InMemoryPost;
import whitesoftapp.whitesoftapp.utils.DtoUtils;

@RequiredArgsConstructor
@Service
public class PostService {

    private final InMemoryPost inMemoryPost;

    public DtoEntity createPost(DtoEntity createPostDto) {
        Post post=(Post)  new DtoUtils().convertToEntity(new Post(),createPostDto);
        inMemoryPost.add(post);
        return new DtoUtils().convertToDto(post, new CreatePostDto());
    }

}
