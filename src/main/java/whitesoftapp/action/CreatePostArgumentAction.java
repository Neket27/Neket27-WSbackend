package whitesoftapp.action;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import whitesoftapp.arguments.CreatePostArgument;
import whitesoftapp.controller.utils.mapper.post.PostMapper;
import whitesoftapp.model.dtos.post.CreatePostDto;

@RequiredArgsConstructor
@Component
public class CreatePostArgumentAction {

    private final PostMapper postMapper;

    public CreatePostArgument create(CreatePostDto createPostDto){
        return postMapper.convertToCreatePostArgument(createPostDto);
    }

}
