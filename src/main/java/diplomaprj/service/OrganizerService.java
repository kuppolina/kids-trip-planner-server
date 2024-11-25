package diplomaprj.service;

import diplomaprj.entity.KidNote;
import diplomaprj.entity.Note;
import diplomaprj.entity.Organizer;
import diplomaprj.entity.Parent;
import diplomaprj.entity.Participant;
import diplomaprj.entity.ParticipantsTrips;
import diplomaprj.entity.Task;
import diplomaprj.repository.KidNoteRepository;
import diplomaprj.repository.NoteRepository;
import diplomaprj.repository.OrganizerRepository;
import diplomaprj.repository.ParentRepository;
import diplomaprj.repository.ParticipantRepository;
import diplomaprj.repository.ParticipantsTripsRepository;
import diplomaprj.repository.TaskRepository;
import diplomaprj.repository.TripRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrganizerService {

  private final Logger log = LoggerFactory.getLogger(OrganizerService.class);

  private final OrganizerRepository organizerRepository;

  private final ParticipantRepository participantRepository;

  private final TaskRepository taskRepository;

  private final NoteRepository noteRepository;

  private final KidNoteRepository kidNoteRepository;

  private final TripRepository tripRepository;

  private final ParentRepository parentRepository;

  private final ParticipantsTripsRepository participantsTripsRepository;

  @Autowired
  public OrganizerService(@Qualifier("organizerRepository") OrganizerRepository organizerRepository,
      @Qualifier("participantRepository") ParticipantRepository participantRepository,
      @Qualifier("taskRepository") TaskRepository taskRepository,
      @Qualifier("noteRepository") NoteRepository noteRepository,
      @Qualifier("tripRepository") TripRepository tripRepository,
      @Qualifier("parentRepository") ParentRepository parentRepository,
      @Qualifier("kidNoteRepository") KidNoteRepository kidNoteRepository,
      @Qualifier("participantsTripsRepository") ParticipantsTripsRepository participantsTripsRepository) {
    this.organizerRepository = organizerRepository;
    this.participantRepository = participantRepository;
    this.taskRepository = taskRepository;
    this.noteRepository = noteRepository;
    this.kidNoteRepository = kidNoteRepository;
    this.tripRepository = tripRepository;
    this.parentRepository = parentRepository;
    this.participantsTripsRepository = participantsTripsRepository;
  }

  public Organizer createOrganizer(Organizer newOrganizer) {
    newOrganizer.setToken(UUID.randomUUID().toString());
    checkIfOrganizerExists(newOrganizer);

    newOrganizer = organizerRepository.save(newOrganizer);

    log.debug("Created Information for Organizer: {}", newOrganizer);
    return newOrganizer;
  }

  public Parent createParent(Parent newParent) {
    newParent = parentRepository.save(newParent);

    log.debug("Created Information for Parent: {}", newParent);
    return newParent;
  }

  public void deleteParent(Long participantId) {
    parentRepository.deleteByParticipantId(participantId);
  }

  public List<Parent> getParentsByParticipantId(Long participantId) {
    return parentRepository.findParentsByParticipantId(participantId);
  }

  public Participant createParticipant(Participant newParticipant) {
    newParticipant = participantRepository.save(newParticipant);

    log.debug("Created Information for Participant: {}", newParticipant);
    return newParticipant;
  }

  // do not forget to set the username from the local storage of the organizer so
  // it can be updated
  public void updateOrganizer(Organizer updatedOrganizer) {
    Organizer organizerToBeUpdated = organizerRepository.findOrganizerById(updatedOrganizer.getId());

    organizerToBeUpdated.setName(updatedOrganizer.getName());
    organizerToBeUpdated.setAddress(updatedOrganizer.getAddress());
    organizerToBeUpdated.setEmail(updatedOrganizer.getEmail());

    organizerRepository.save(organizerToBeUpdated);

    log.debug("Update Information for User: {}", organizerToBeUpdated);
  }

  public void updateNotes(Long organizerId, Note newNote) {
    organizerRepository.findById(organizerId)
        .orElseThrow(() -> new ResourceNotFoundException("Organizer not found with id " + organizerId));

    newNote.setOrganizerId(organizerId);
    noteRepository.save(newNote);

  }

  public void updateKidNotes(Long organizerId, Long kidId, KidNote newNote) {
    organizerRepository.findById(organizerId)
        .orElseThrow(() -> new ResourceNotFoundException("Organizer not found with id " + organizerId));

    participantRepository.findById(kidId)
        .orElseThrow(() -> new ResourceNotFoundException("Kid not found with id " + kidId));

    newNote.setOrganizerId(organizerId);
    newNote.setParticipantId(kidId);
    kidNoteRepository.save(newNote);

  }

  public void updateTasks(Long organizerId, Task newTask) {
    organizerRepository.findById(organizerId)
        .orElseThrow(() -> new ResourceNotFoundException("Organizer not found with id " + organizerId));

    newTask.setOrganizerId(organizerId);
    taskRepository.save(newTask);
  }

  public void emptyNotes(Long organizerId) {
    organizerRepository.findById(organizerId)
        .orElseThrow(() -> new ResourceNotFoundException("Organizer not found with id " + organizerId));

    noteRepository.deleteAllByOrganizerId(organizerId);
  }

  public void emptyTasks(Long organizerId) {
    organizerRepository.findById(organizerId)
        .orElseThrow(() -> new ResourceNotFoundException("Organizer not found with id " + organizerId));

    taskRepository.deleteAllByOrganizerId(organizerId);
  }

  public void emptyKidNotes(Long participantId) {
    participantRepository.findById(participantId)
        .orElseThrow(() -> new ResourceNotFoundException("Kid not found with id " + participantId));

    kidNoteRepository.deleteByParticipantId(participantId);
  }

  // do not forget to set the id from the local storage of the participant so it
  // can be updated
  public void updateParticipant(Participant updatedParticipant) {
    Participant participantToBeUpdated = participantRepository.findParticipantById(updatedParticipant.getId());

    participantToBeUpdated.setNotes(updatedParticipant.getNotes());
    participantToBeUpdated.setDateOfBirth(updatedParticipant.getDateOfBirth());
    participantToBeUpdated.setAddress(updatedParticipant.getAddress());

    // deciede how to update the parents
    participantRepository.save(participantToBeUpdated);

    log.debug("Update Information for User: {}", participantToBeUpdated);
  }

  public List<Participant> getParticipants(Long organizerId) {
    return participantRepository.findByOrganizerId(organizerId);
  }

  public Participant getParticipantById(Long participantId) {
    Participant participant = participantRepository.findParticipantById(participantId);
    participant.setKidNotes(kidNoteRepository.findByParticipantId(participantId));
    return participant;
  }

  public Organizer getOrganizerById(Long organizerId) {
    Organizer organizer = organizerRepository.findOrganizerById(organizerId);
    organizer.setNotes(noteRepository.findByOrganizerId(organizerId));
    organizer.setTasks(taskRepository.findByOrganizerId(organizerId));
    organizer.setMyTrips(tripRepository.findAllByOrganizerId(organizerId));
    return organizer;
  }

  public void logout() {

  }

  public Organizer login(Organizer organizerInput) {
    checkIfOrganizerRegistered(organizerInput);

    Organizer organizer = this.organizerRepository.findByUsername(organizerInput.getUsername());
    return organizer;
  }

  private void checkIfOrganizerExists(Organizer organizerToBeCreated) {
    Organizer organizerByUsername = organizerRepository.findByUsername(organizerToBeCreated.getUsername());

    // there is a user with the same username in the system
    if (organizerByUsername != null) {
      throw new ResponseStatusException(HttpStatus.CONFLICT,
          String.format("The username provided is not unique. Therefore, the account could not be created!"));
    }
  }

  private void checkIfOrganizerRegistered(Organizer organizerToBeLoggedIn) {
    Organizer organizerByOrganizerUsername = organizerRepository.findByUsername(organizerToBeLoggedIn.getUsername());
    Organizer organizerByOrganizerUsernameAndPassword = organizerRepository.findByUsernameAndPassword(
        organizerToBeLoggedIn.getUsername(),
        organizerToBeLoggedIn.getPassword());

    String baseErrorMessage = "Incorrect credentials, please try again, as %s: '%s' does not match.";

    if (organizerByOrganizerUsername == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          String.format(baseErrorMessage, "username", organizerToBeLoggedIn.getUsername()));
    } else if (organizerByOrganizerUsernameAndPassword == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          String.format(baseErrorMessage, "password", organizerToBeLoggedIn.getPassword()));
    }
  }

  public void saveAccessToken(String username, String accessToken) {
    Organizer organizer = organizerRepository.findByUsername(username);
    organizer.setEventbriteToken(accessToken);
    organizerRepository.save(organizer);
  }

  public String getAccessToken(String username) {
    Organizer organizer = organizerRepository.findByUsername(username);
    return organizer.getEventbriteToken();
  }

  public Organizer updateOrganizerPhoto(MultipartFile image, Long organizerId) throws IOException {
    Organizer organizer = organizerRepository.findById(organizerId)
        .orElseThrow(() -> new ResourceNotFoundException("Organizer not found with id " + organizerId));

    String filename = image.getOriginalFilename();
    String uploadDir = "uploads/";

    File uploadFile = new File(uploadDir + filename);
    image.transferTo(uploadFile);

    organizer.setImage("/uploads/" + filename);
    return organizerRepository.save(organizer);
  }

  public void deleteOrganizer(Long organizerId) {
    Organizer organizerToDelete = organizerRepository.findOrganizerById(organizerId);
    if (organizerToDelete == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No prganizer with such id, cannot delete");
    }
    this.organizerRepository.deleteById(organizerId);
  }

  public void deleteParticipant(Long organizerId, Long participantId) {
    Participant participantToDelete = participantRepository.findParticipantById(participantId);
    if (participantToDelete == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No participant with such id, cannot delete");
    }
    this.participantRepository.deleteById(participantId);
  }

  public List<Participant> getParticipantsByTripId(Long tripId) {
    List<ParticipantsTrips> participantsTrips = participantsTripsRepository.findByTripId(tripId);
    return participantsTrips.stream()
        .map(pt -> participantRepository.findById(pt.getParticipantId()).orElse(null))
        .collect(Collectors.toList());
  }
}