package diplomaprj.controller;

import diplomaprj.entity.Participant;
import diplomaprj.entity.Trip;
import diplomaprj.rest.dto.SelectionParticipantPostDTO;
import diplomaprj.rest.dto.TripGetDTO;
import diplomaprj.rest.dto.TripPostDTO;
import diplomaprj.rest.dto.TripPutDTO;
import diplomaprj.rest.mapper.DTOMapper;
import diplomaprj.service.OrganizerService;
import diplomaprj.service.TripService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.client.RestTemplate;

@RestController
public class TripController {

    private final Logger log = LoggerFactory.getLogger(TripController.class);

    private final TripService tripService;

    private final OrganizerService organizerService;

    private final RestTemplate restTemplate;

    public TripController(TripService tripService, RestTemplate restTemplate,
            OrganizerService organizerService) {
        this.tripService = tripService;
        this.organizerService = organizerService;
        this.restTemplate = restTemplate;
    }

    // returns all the trips of the ogranizer
    @GetMapping("/organizers/{organizerId}/trips")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<TripGetDTO> getTripsById(@PathVariable Long organizerId) {
        List<Trip> trips = tripService.getTrips(organizerId);
        List<TripGetDTO> tripsGetDTOs = new ArrayList<>();

        // convert each user to the API representation
        if (trips != null) {
            for (Trip trip : trips) {
                tripsGetDTOs.add(DTOMapper.INSTANCE.convertEntityToTripGetDTO(trip));
            }
            return tripsGetDTOs;
        }
        return null;
    }

    // returns all finished trips of the ogranizer
    @GetMapping("/organizers/{organizerId}/finishedtrips")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<TripGetDTO> getFinishedTripsById(@PathVariable Long organizerId) {
        List<Trip> trips = tripService.getFinishedTrips(organizerId);
        List<TripGetDTO> tripsGetDTOs = new ArrayList<>();

        // convert each user to the API representation
        if (trips != null) {
            for (Trip trip : trips) {
                tripsGetDTOs.add(DTOMapper.INSTANCE.convertEntityToTripGetDTO(trip));
            }
            return tripsGetDTOs;
        }
        return null;
    }

    // returns all drafts trips of the ogranizer
    @GetMapping("/organizers/{organizerId}/draftstrips")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<TripGetDTO> getDraftsTripsById(@PathVariable Long organizerId) {
        List<Trip> trips = tripService.getDraftsTrips(organizerId);
        List<TripGetDTO> tripsGetDTOs = new ArrayList<>();

        // convert each user to the API representation
        if (trips != null) {
            for (Trip trip : trips) {
                tripsGetDTOs.add(DTOMapper.INSTANCE.convertEntityToTripGetDTO(trip));
            }
            return tripsGetDTOs;
        }
        return null;
    }

    // create trip
    @PostMapping("/trips")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public TripGetDTO createTrip(@RequestBody TripPostDTO tripPostDTO) {
        // convert API user to internal representation
        Trip tripInput = DTOMapper.INSTANCE.convertTripPostDTOtoEntity(tripPostDTO);

        Trip createdTrip = tripService.createTrip(tripInput);
        return DTOMapper.INSTANCE.convertEntityToTripGetDTO(createdTrip);
    }

    // for trip overview
    @GetMapping("/organizers/{organizerId}/trips/{tripId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public TripGetDTO getTripById(@PathVariable Long tripId) {
        // get patricipant by id
        Trip tripById = tripService.getTripById(tripId);

        // convert internal representation of user back to API
        return DTOMapper.INSTANCE.convertEntityToTripGetDTO(tripById);
    }

    // update trip information
    @PutMapping("/trips/{tripId}/update")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTrip(@RequestBody TripPutDTO tripPutDTO, @PathVariable Long tripId) {
        Trip tripInput = DTOMapper.INSTANCE.convertTripPutDTOtoEntity(tripPutDTO);
        tripInput.setId(tripId);
        tripService.updateTrip(tripInput);
    }

    @DeleteMapping("/organizers/{organizerId}/trips/{tripId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTrip(@PathVariable Long organizerId, @PathVariable Long tripId) {
        tripService.deleteTrip(organizerId, tripId);
    }

    @PostMapping("/trips/{tripId}/participants")
    public void addParticipantToTrip(@PathVariable Long tripId, @RequestBody List<SelectionParticipantPostDTO> selectedParticipants) {
        List<Participant> participants = new ArrayList<>();
        if(selectedParticipants != null){
            for (SelectionParticipantPostDTO participantPostDTO : selectedParticipants) {
                participants.add(DTOMapper.INSTANCE.convertSelectionParticipantPostDTOtoEntity(participantPostDTO));
            }
        }
    
        tripService.updateParticipantsInTrip(tripId, participants);
    }

    @GetMapping("/trips/{tripId}/participants")
    public List<Participant> getParticipantsByTripId(@PathVariable Long tripId) {
        return organizerService.getParticipantsByTripId(tripId);
    }
}