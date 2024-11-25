package diplomaprj.rest.mapper;

import diplomaprj.entity.KidNote;
import diplomaprj.entity.Note;
import diplomaprj.entity.Organizer;
import diplomaprj.entity.Parent;
import diplomaprj.entity.Trip;
import diplomaprj.entity.Participant;
import diplomaprj.entity.Task;
import diplomaprj.rest.dto.KidNoteGetDTO;
import diplomaprj.rest.dto.KidNotePutDTO;
import diplomaprj.rest.dto.NoteGetDTO;
import diplomaprj.rest.dto.NotePutDTO;
import diplomaprj.rest.dto.OrganizerGetDTO;
import diplomaprj.rest.dto.OrganizerPostDTO;
import diplomaprj.rest.dto.OrganizerPutDTO;
import diplomaprj.rest.dto.ParentGetDTO;
import diplomaprj.rest.dto.ParentPostDTO;
import diplomaprj.rest.dto.TripGetDTO;
import diplomaprj.rest.dto.TripPostDTO;
import diplomaprj.rest.dto.TripPutDTO;
import diplomaprj.rest.dto.ParticipantGetDTO;
import diplomaprj.rest.dto.ParticipantPostDTO;
import diplomaprj.rest.dto.ParticipantPutDTO;
import diplomaprj.rest.dto.SelectionParticipantPostDTO;
import diplomaprj.rest.dto.TaskGetDTO;
import diplomaprj.rest.dto.TaskPutDTO;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DTOMapper {

  DTOMapper INSTANCE = Mappers.getMapper(DTOMapper.class);

  @Mapping(source = "username", target = "username")
  @Mapping(source = "password", target = "password")
  Organizer convertOrganizerPostDTOtoEntity(OrganizerPostDTO organizerPostDTO);

  @Mapping(source = "parentName", target = "parentName")
  @Mapping(source = "address", target = "address")
  @Mapping(source = "participantId", target = "participantId")
  @Mapping(source = "email", target = "email")
  @Mapping(source = "phoneNumber", target = "phoneNumber")
  @Mapping(source = "relationship", target = "relationship")
  Parent convertParentPostDTOtoEntity(ParentPostDTO parentPostDTO);

  @Mapping(source = "id", target = "id")
  @Mapping(source = "parentName", target = "parentName")
  @Mapping(source = "address", target = "address")
  @Mapping(source = "participantId", target = "participantId")
  @Mapping(source = "email", target = "email")
  @Mapping(source = "phoneNumber", target = "phoneNumber")
  @Mapping(source = "relationship", target = "relationship")
  ParentGetDTO convertEntityToParentGetDTO(Parent parent);

  @Mapping(source = "id", target = "id")
  @Mapping(source = "name", target = "name")
  @Mapping(source = "username", target = "username")
  @Mapping(source = "token", target = "token")
  @Mapping(source = "image", target = "image")
  @Mapping(source = "myTrips", target = "myTrips")
  @Mapping(source = "notes", target = "notes")
  @Mapping(source = "tasks", target = "tasks")
  @Mapping(source = "email", target = "email")
  @Mapping(source = "address", target = "address")
  @Mapping(source = "myParticipants", target = "myParticipants")
  OrganizerGetDTO convertEntityToOrganizerGetDTO(Organizer organizer);

  @Mapping(source = "title", target = "title")
  @Mapping(source = "image", target = "image")
  @Mapping(source = "address", target = "address")
  @Mapping(source = "organizerId", target = "organizerId")
  @Mapping(source = "numberOfKids", target = "numberOfKids")
  @Mapping(source = "description", target = "description")
  Trip convertTripPostDTOtoEntity(TripPostDTO tripPostDTO);

  @Mapping(source = "id", target = "id")
  @Mapping(source = "title", target = "title")
  @Mapping(source = "status", target = "status")
  @Mapping(source = "address", target = "address")
  @Mapping(source = "image", target = "image")
  @Mapping(source = "numberOfKids", target = "numberOfKids")
  @Mapping(source = "description", target = "description")
  @Mapping(source = "organizerId", target = "organizerId")
  TripGetDTO convertEntityToTripGetDTO(Trip trip);

  @Mapping(source = "name", target = "name")
  @Mapping(source = "address", target = "address")
  @Mapping(source = "dateOfBirth", target = "dateOfBirth")
  @Mapping(source = "age", target = "age")
  @Mapping(source = "notes", target = "notes")
  @Mapping(source = "organizerId", target = "organizerId")
  @Mapping(source = "image", target = "image")
  Participant convertParticipantPostDTOtoEntity(ParticipantPostDTO participantPostDTO);

  @Mapping(source = "id", target = "id")
  Participant convertSelectionParticipantPostDTOtoEntity(SelectionParticipantPostDTO selectionParticipantPostDTO);

  @Mapping(source = "id", target = "id")
  @Mapping(source = "name", target = "name")
  @Mapping(source = "dateOfBirth", target = "dateOfBirth")
  @Mapping(source = "address", target = "address")
  @Mapping(source = "image", target = "image")
  @Mapping(source = "age", target = "age")
  @Mapping(source = "notes", target = "notes")
  @Mapping(source = "kidNotes", target = "kidNotes")
  @Mapping(source = "parent", target = "parent")
  ParticipantGetDTO convertEntityToParticipantGetDTO(Participant participant);

  @Mapping(source = "name", target = "name")
  @Mapping(source = "address", target = "address")
  @Mapping(source = "email", target = "email")
  Organizer convertOrganizerPutDTOtoEntity(OrganizerPutDTO organizerPutDTO);

  @Mapping(source = "address", target = "address")
  @Mapping(source = "dateOfBirth", target = "dateOfBirth")
  @Mapping(source = "notes", target = "notes")
  Participant convertParticipantPutDTOtoEntity(ParticipantPutDTO participantPutDTO);

  @Mapping(source = "status", target = "status")
  @Mapping(source = "description", target = "description")
  @Mapping(source = "address", target = "address")
  Trip convertTripPutDTOtoEntity(TripPutDTO tripPutDTO);

  @Mapping(source = "content", target = "content")
  Note convertNotePutDTOtoEntity(NotePutDTO notePutDTO);

  @Mapping(source = "content", target = "content")
  Task convertTaskPutDTOtoEntity(TaskPutDTO taskPutDTO);

  @Mapping(source = "content", target = "content")
  @Mapping(source = "organizerId", target = "organizerId")
  NoteGetDTO convertEntityToNoteGetDTO(Note note);

  @Mapping(source = "content", target = "content")
  @Mapping(source = "organizerId", target = "organizerId")
  TaskGetDTO convertEntityToTaskGetDTO(Task task);

  @Mapping(source = "content", target = "content")
  KidNote convertKidNotePutDTOtoEntity(KidNotePutDTO kidNotePutDTO);

  @Mapping(source = "content", target = "content")
  @Mapping(source = "organizerId", target = "organizerId")
  KidNoteGetDTO convertEntityToKidNoteGetDTO(KidNote kidNote);
  
}
