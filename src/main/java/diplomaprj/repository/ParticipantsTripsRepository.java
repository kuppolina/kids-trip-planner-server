package diplomaprj.repository;

import org.springframework.stereotype.Repository;
import diplomaprj.entity.ParticipantsTrips;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import javax.transaction.Transactional;


import java.util.List;

import javax.transaction.Transactional;

@Transactional
@RepositoryRestResource
@Repository("participantsTripsRepository")
public interface ParticipantsTripsRepository extends CrudRepository<ParticipantsTrips, Long> {
    List<ParticipantsTrips> findByTripId(Long tripId);
    boolean existsByTripIdAndParticipantId(Long tripId, Long participantId);
}
