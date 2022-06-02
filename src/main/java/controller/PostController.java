package controller;

import lombok.RequiredArgsConstructor;
import model.dtos.CreatePostDto;
import model.dtos.DtoEntity;
import service.PostService;

@RequiredArgsConstructor
public class PostController implements DtoEntity {

    private final PostService postService;

    public DtoEntity createPost(CreatePostDto createPostDto){
        return postService.createPost(createPostDto);
    }
}
