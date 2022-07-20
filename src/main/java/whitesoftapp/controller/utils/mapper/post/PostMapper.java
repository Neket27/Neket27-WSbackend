package whitesoftapp.controller.utils.mapper.post;

import org.mapstruct.Mapper;
import whitesoftapp.arguments.CreatePostArgument;
import whitesoftapp.model.Post;
import whitesoftapp.model.dtos.post.CreatePostDto;
import whitesoftapp.model.dtos.post.PostDto;

@Mapper(componentModel = "spring")
public interface PostMapper {

    PostDto toDto(Post post);

    Post toEntity(PostDto postDto);

    Post toEntityFromCreatePostDto(CreatePostDto createPostDto);

    CreatePostArgument convertToCreatePostArgument(CreatePostDto createPostDto);

    Post toEntityFromCreatePostArgument(CreatePostArgument createPostArgument);

}
