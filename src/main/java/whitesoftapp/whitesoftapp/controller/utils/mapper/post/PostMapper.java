package whitesoftapp.whitesoftapp.controller.utils.mapper.post;

import org.mapstruct.Mapper;
import whitesoftapp.whitesoftapp.model.Post;
import whitesoftapp.whitesoftapp.model.dtos.post.PostDto;

@Mapper(componentModel = "spring")
public interface PostMapper {

    PostDto toDto(Post post);

    Post toEntity(PostDto postDto);
}
