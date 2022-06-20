package whitesoftapp.whitesoftapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import whitesoftapp.whitesoftapp.model.dtos.post.PostDto;
import whitesoftapp.whitesoftapp.service.PostService;

import javax.validation.Valid;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @PostMapping("/createPost")
    public PostDto createPost(@Valid @RequestBody PostDto createPostDto) {
        return postService.createPost(createPostDto);
    }

    @GetMapping("/getPost/{id}")
    public PostDto getById(@PathVariable UUID id) throws Exception {
        return postService.getById(id);
    }
}
