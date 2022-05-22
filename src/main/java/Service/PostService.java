package Service;

import EmployeeCard.Post;
import java.util.UUID;

public interface PostService {

    void cretePost();
    Post createPost(UUID uuid, String name);

    }
