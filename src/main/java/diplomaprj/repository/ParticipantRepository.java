package diplomaprj.repository;

import diplomaprj.entity.Participant;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@Transactional
@RepositoryRestResource
@Repository("participantRepository")
public interface ParticipantRepository extends CrudRepository<Participant, Long> {
    Participant findByName(String name);

    Participant findParticipantById(Long participantId);

    List<Participant> findByOrganizerId(Long organizerId);

    // Participant save(Participant participant);

}
