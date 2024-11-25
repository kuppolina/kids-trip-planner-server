package diplomaprj.repository;

import diplomaprj.entity.Note;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;

@Transactional
@RepositoryRestResource
@Repository("noteRepository")
public interface NoteRepository extends CrudRepository<Note, Long> {
    List<Note> findByOrganizerId(Long organizerId);

    void deleteAllByOrganizerId(Long organizerId);

}
