package diplomaprj.repository;

import diplomaprj.entity.KidNote;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;

@Transactional
@RepositoryRestResource
@Repository("kidNoteRepository")
public interface KidNoteRepository extends CrudRepository<KidNote, Long> {
    List<KidNote> findByParticipantId(Long participantId);

    void deleteByParticipantId(Long participantId);

}