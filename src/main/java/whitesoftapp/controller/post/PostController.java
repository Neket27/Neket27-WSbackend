package whitesoftapp.controller.post;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import whitesoftapp.action.CreatePostArgumentAction;
import whitesoftapp.controller.utils.mapper.post.PostMapper;
import whitesoftapp.model.dtos.post.CreatePostDto;
import whitesoftapp.model.dtos.post.PostDto;
import whitesoftapp.service.post.PostService;

import javax.validation.Valid;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final CreatePostArgumentAction createPostArgumentAction;
    private final PostMapper postMapper;

    @PostMapping("/create")
    public PostDto createPost(@Valid @RequestBody CreatePostDto createPostDto) {
        return postMapper.toDto(postService.create(createPostArgumentAction.create(createPostDto)));
    }

    @GetMapping("/{id}")
    public PostDto getById(@PathVariable UUID id) {
        return postMapper.toDto(postService.getById(id));
    }
}
