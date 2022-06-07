package whitesoftapp.whitesoftapp.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import whitesoftapp.whitesoftapp.model.Post;
import whitesoftapp.whitesoftapp.model.dtos.PostDto;

@RequiredArgsConstructor
@Component
public class DtoUtilsPost {

    private final ObjectMapper objectMapper;

    public PostDto convertToDto(Post post, PostDto mapper) {
        return objectMapper.convertValue(post, mapper.getClass());
    }

    public Post convertToEntity(Post post, PostDto mapper) {
        return objectMapper.convertValue(mapper,post.getClass());
    }
}
