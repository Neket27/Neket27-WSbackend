package service;

import model.Post;
import model.dtos.CreatePostDto;
import model.dtos.DtoEntity;
import org.springframework.stereotype.Service;
import utils.DtoUtils;

@Service
public class PostService {

    public DtoEntity createPost(DtoEntity createPostDto) {
        Post post=(Post)  new DtoUtils().convertToEntity(new Post(),createPostDto);
        return new DtoUtils().convertToDto(post, new CreatePostDto());
    }

}
