package diplomaprj.repository;

import diplomaprj.entity.Parent;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@Transactional
@RepositoryRestResource
@Repository("parentRepository")
public interface ParentRepository extends CrudRepository<Parent, Long> {
    Parent findParentById(Long parentId);

    List<Parent> findParentsByParticipantId(Long participantId);

    void deleteByParticipantId(Long participantId);

}