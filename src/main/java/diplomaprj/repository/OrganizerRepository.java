package diplomaprj.repository;

import diplomaprj.entity.Organizer;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@Transactional
@RepositoryRestResource
@Repository("organizerRepository")
public interface OrganizerRepository extends CrudRepository<Organizer, Long> {
    Organizer findByName(String name);

    Organizer findByUsername(String username);

    Organizer findOrganizerById(Long organizerId);

    Organizer findByUsernameAndPassword(String username, String password);

}
