package whitesoftapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
@NoRepositoryBean
public interface PostRepository extends JpaRepository<PostRepository, UUID> {

}
