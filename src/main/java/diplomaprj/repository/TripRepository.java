package diplomaprj.repository;

import diplomaprj.entity.Trip;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import diplomaprj.constant.TripStatus;

import java.util.List;

@Transactional
@RepositoryRestResource
@Repository("tripRepository")
public interface TripRepository extends CrudRepository<Trip, Long> {

    List<Trip> findAllByOrganizerId(Long organizerId);

    List<Trip> findByOrganizerIdAndStatus(Long organizerId, TripStatus tripStatus);

    Trip findTripById(Long tripId);

}
