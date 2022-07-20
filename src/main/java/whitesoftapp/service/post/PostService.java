package whitesoftapp.service.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import whitesoftapp.arguments.CreatePostArgument;
import whitesoftapp.controller.utils.mapper.post.PostMapper;
import whitesoftapp.exception.ErrorException;
import whitesoftapp.model.Post;
import whitesoftapp.repository.PostRepository;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public Post create(CreatePostArgument createPostArgument) {
        Post post = postMapper.toEntityFromCreatePostArgument(createPostArgument);
        return postRepository.save(post);
    }

    public Post getById(UUID id) {
        return postRepository.findById(id).orElseThrow(() -> new ErrorException("Нет Поста с таким id"));

    }

}
