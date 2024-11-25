package diplomaprj.service;

import diplomaprj.entity.Participant;
import diplomaprj.entity.ParticipantsTrips;
import diplomaprj.entity.Trip;
import diplomaprj.repository.ParticipantRepository;
import diplomaprj.repository.ParticipantsTripsRepository;
import diplomaprj.repository.TripRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import diplomaprj.constant.TripStatus;

import java.util.UUID;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TripService {

    private final Logger log = LoggerFactory.getLogger(TripService.class);

    private final TripRepository tripRepository;

    private final ParticipantRepository participantRepository;

    private final ParticipantsTripsRepository participantsTripsRepository;

    @Autowired
    public TripService(@Qualifier("tripRepository") TripRepository tripRepository,
            @Qualifier("participantRepository") ParticipantRepository participantRepository,
            @Qualifier("participantsTripsRepository") ParticipantsTripsRepository participantsTripsRepository) {
        this.tripRepository = tripRepository;
        this.participantRepository = participantRepository;
        this.participantsTripsRepository = participantsTripsRepository;
    }

    public Trip createTrip(Trip newTrip) {
        newTrip.setStatus(TripStatus.DRAFT);

        newTrip = tripRepository.save(newTrip);

        log.debug("Created Information for Organizer: {}", newTrip);
        return newTrip;
    }

    // do not forget to set the id from the local storage of the trip so it can be
    // updated
    public void updateTrip(Trip updatedTrip) {
        Trip tripToBeUpdated = tripRepository.findTripById(updatedTrip.getId());

        tripToBeUpdated.setDescription(updatedTrip.getDescription());
        tripToBeUpdated.setAddress(updatedTrip.getAddress());
        tripToBeUpdated.setStatus(updatedTrip.getStatus());

        tripRepository.save(tripToBeUpdated);

        log.debug("Update Information for Trip: {}", tripToBeUpdated);
    }

    public List<Trip> getTrips(Long organizerId) {
        return this.tripRepository.findAllByOrganizerId(organizerId);
    }

    public List<Trip> getFinishedTrips(Long organizerId) {
        return this.tripRepository.findByOrganizerIdAndStatus(organizerId, TripStatus.FINISHED);
    }

    public List<Trip> getDraftsTrips(Long organizerId) {
        return this.tripRepository.findByOrganizerIdAndStatus(organizerId, TripStatus.DRAFT);
    }

    public Trip getTripById(Long tripId) {
        return this.tripRepository.findTripById(tripId);

    }

    public void deleteTrip(Long organizerId, Long tripId) {
        Trip tripToDelete = tripRepository.findTripById(tripId);
        if (tripToDelete == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No trip with such id, cannot delete");
        }
        this.tripRepository.deleteById(tripId);
    }

    public List<Trip> searchTrips(String query, Long organizerId) {
        List<Trip> result = new ArrayList<>();
        List<Trip> trips = this.tripRepository.findAllByOrganizerId(organizerId);
        String lowerCaseQuery = query.toLowerCase();

        if (trips != null) {
            for (Trip trip : trips) {
                if (trip.getTitle().toLowerCase().contains(lowerCaseQuery) ||
                        trip.getDescription().toLowerCase().contains(lowerCaseQuery)) {
                    result.add(trip);
                }
            }
        }
        return result;
    }

    @Transactional
    public void updateParticipantsInTrip(Long tripId, List<Participant> participants) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid trip Id:" + tripId));

        // list of all participants ids received by the server with old pairs       
        List<Long> newParticipantIds = participants.stream().map(Participant::getId).collect(Collectors.toList());
        if ( newParticipantIds.size() > Integer.parseInt(trip.getNumberOfKids())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Cannot add new participants, select less");
        }

        // list of all participants from db       
        List<ParticipantsTrips> existingParticipantsTrips = participantsTripsRepository.findByTripId(tripId);

        // Remove participants that are no longer in the list
        for (ParticipantsTrips existingParticipantTrip : existingParticipantsTrips) {
            if (!newParticipantIds.contains(existingParticipantTrip.getParticipantId())) {
                participantsTripsRepository.delete(existingParticipantTrip);
            }
        }

        // Add new participants that are only in the list
        for (Participant participant : participants) {
            Long participantId = participant.getId();
            boolean exists = participantsTripsRepository.existsByTripIdAndParticipantId(tripId, participantId);

            if (!exists) {
                ParticipantsTrips participantTrip = new ParticipantsTrips(participantId, tripId);
                participantsTripsRepository.save(participantTrip);
            }
        }
    }
}