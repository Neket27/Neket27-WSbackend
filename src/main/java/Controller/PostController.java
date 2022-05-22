package Controller;

import Dto.CreatePostDto;
import EmployeeCard.Post;
import Service.PostService;

public class PostController  {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    public Post createPost(CreatePostDto createPostDto){
       return postService.createPost(createPostDto.getId(),createPostDto.getName());
     }

}
