package whitesoftapp.whitesoftapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import whitesoftapp.whitesoftapp.model.dtos.PostDto;
import whitesoftapp.whitesoftapp.service.PostService;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    public PostDto createPost(PostDto createPostDto) {
        return postService.createPost(createPostDto);
    }
}
