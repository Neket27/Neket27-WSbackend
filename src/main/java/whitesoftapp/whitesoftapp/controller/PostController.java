package whitesoftapp.whitesoftapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import whitesoftapp.whitesoftapp.model.dtos.CreatePostDto;
import whitesoftapp.whitesoftapp.model.dtos.DtoEntity;
import whitesoftapp.whitesoftapp.service.PostService;

@RequiredArgsConstructor
@RestController
public class PostController implements DtoEntity {

    private final PostService postService;

    public DtoEntity createPost(CreatePostDto createPostDto) {
        return postService.createPost(createPostDto);
    }
}
