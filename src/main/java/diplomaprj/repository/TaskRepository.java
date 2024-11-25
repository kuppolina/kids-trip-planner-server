package diplomaprj.repository;

import diplomaprj.entity.Task;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;

@Transactional
@RepositoryRestResource
@Repository("taskRepository")
public interface TaskRepository extends CrudRepository<Task, Long> {
    List<Task> findByOrganizerId(Long organizerId);

    void deleteAllByOrganizerId(Long organizerId);

}
