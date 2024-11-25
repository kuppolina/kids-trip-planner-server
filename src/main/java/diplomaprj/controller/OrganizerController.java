package diplomaprj.controller;

import diplomaprj.entity.KidNote;
import diplomaprj.entity.Note;
import diplomaprj.entity.Organizer;
import diplomaprj.entity.Participant;
import diplomaprj.entity.Task;
import diplomaprj.rest.dto.KidNotePutDTO;
import diplomaprj.rest.dto.NotePutDTO;
import diplomaprj.rest.dto.OrganizerGetDTO;
import diplomaprj.rest.dto.ParticipantGetDTO;
import diplomaprj.rest.dto.OrganizerPostDTO;
import diplomaprj.rest.dto.ParticipantPostDTO;
import diplomaprj.rest.dto.OrganizerPutDTO;
import diplomaprj.rest.dto.ParentGetDTO;
import diplomaprj.rest.dto.ParentPostDTO;
import diplomaprj.rest.dto.ParticipantPutDTO;
import diplomaprj.rest.dto.TaskPutDTO;
import diplomaprj.rest.mapper.DTOMapper;
import diplomaprj.service.OrganizerService;

import diplomaprj.entity.Parent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class OrganizerController {

    private final Logger log = LoggerFactory.getLogger(OrganizerController.class);

    private final OrganizerService organizerService;

    public OrganizerController(OrganizerService organizerService) {
        this.organizerService = organizerService;
    }

    // returns all the participants of the ogranizer
    @GetMapping("/organizers/{organizerId}/participants")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<ParticipantGetDTO> getParticipantsById(@PathVariable Long organizerId) {
        List<Participant> participants = organizerService.getParticipants(organizerId);
        List<ParticipantGetDTO> participantsGetDTOs = new ArrayList<>();

        // convert each user to the API representation
        for (Participant participant : participants) {
            participantsGetDTOs.add(DTOMapper.INSTANCE.convertEntityToParticipantGetDTO(participant));
        }
        return participantsGetDTOs;
    }

    // create teacher

    @PostMapping("/organizers")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public OrganizerGetDTO createOrganizer(@RequestBody OrganizerPostDTO organizerPostDTO) {
        // convert API user to internal representation
        Organizer organizerInput = DTOMapper.INSTANCE.convertOrganizerPostDTOtoEntity(organizerPostDTO);

        // create user
        Organizer createdOrganizer = organizerService.createOrganizer(organizerInput);
        // convert internal representation of user back to API
        return DTOMapper.INSTANCE.convertEntityToOrganizerGetDTO(createdOrganizer);
    }

    @PostMapping("/parents")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ParentGetDTO createParent(@RequestBody ParentPostDTO parentPostDTO) {
        // convert API user to internal representation
        Parent parentInput = DTOMapper.INSTANCE.convertParentPostDTOtoEntity(parentPostDTO);

        // create user
        Parent createdParent = organizerService.createParent(parentInput);
        // convert internal representation of user back to API
        return DTOMapper.INSTANCE.convertEntityToParentGetDTO(createdParent);
    }

    @DeleteMapping("/parents/{participantId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void deleteParent(@PathVariable Long participantId) {
        organizerService.deleteParent(participantId);
    }


    @GetMapping("/parents/{participantId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<ParentGetDTO> getParentsByParticipantId(@PathVariable Long participantId) {
        List<Parent> parents = organizerService.getParentsByParticipantId(participantId);

        List<ParentGetDTO> parentsGetDTOs = new ArrayList<>();

        // convert each user to the API representation
        for (Parent parent : parents) {
            parentsGetDTOs.add(DTOMapper.INSTANCE.convertEntityToParentGetDTO(parent));
        }
        return parentsGetDTOs;
    }

    // create a kid
    @PostMapping("/participants")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ParticipantGetDTO createParticipant(@RequestBody ParticipantPostDTO participantPostDTO) {
        // convert API user to internal representation
        Participant participantInput = DTOMapper.INSTANCE.convertParticipantPostDTOtoEntity(participantPostDTO);

        // create user
        Participant createdParticipant = organizerService.createParticipant(participantInput);
        // convert internal representation of user back to API
        return DTOMapper.INSTANCE.convertEntityToParticipantGetDTO(createdParticipant);
    }

    // for organizer profile
    @GetMapping("/organizers/{organizerId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public OrganizerGetDTO getOrganizerById(@PathVariable Long organizerId) {
        // get user by id
        Organizer organizerById = organizerService.getOrganizerById(organizerId);

        // convert internal representation of user back to API
        return DTOMapper.INSTANCE.convertEntityToOrganizerGetDTO(organizerById);
    }

    // for participant profile
    @GetMapping("/organizers/{organizerId}/participants/{participantId}")
    @ResponseStatus(HttpStatus.OK)
    public ParticipantGetDTO getParticipantById(@PathVariable Long participantId) {
        Participant participantById = organizerService.getParticipantById(participantId);
        return DTOMapper.INSTANCE.convertEntityToParticipantGetDTO(participantById);
    }

    @PutMapping("/organizers/{organizerId}/updatenotes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateOrganizerNotes(@RequestBody NotePutDTO notePutDTO, @PathVariable Long organizerId) {
        Note newNote = DTOMapper.INSTANCE.convertNotePutDTOtoEntity(notePutDTO);
        organizerService.updateNotes(organizerId, newNote);
    }

    @PutMapping("/organizers/{organizerId}/updatetasks")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateOrganizerTasks(@RequestBody TaskPutDTO taskPutDTO, @PathVariable Long organizerId) {
        Task newTask = DTOMapper.INSTANCE.convertTaskPutDTOtoEntity(taskPutDTO);
        organizerService.updateTasks(organizerId, newTask);
    }

    @PutMapping("/organizers/{organizerId}/participants/{participantId}/updatekidnotes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateKidNotes(@RequestBody KidNotePutDTO kidNotePutDTO, @PathVariable Long organizerId,
            @PathVariable Long participantId) {
        KidNote kidNote = DTOMapper.INSTANCE.convertKidNotePutDTOtoEntity(kidNotePutDTO);
        organizerService.updateKidNotes(organizerId, participantId, kidNote);
    }

    @DeleteMapping("/organizers/{organizerId}/emptynotes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void emptyNotes(@PathVariable Long organizerId) {
        organizerService.emptyNotes(organizerId);
    }

    @DeleteMapping("/organizers/{organizerId}/emptytasks")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void emptyTasks(@PathVariable Long organizerId) {
        organizerService.emptyTasks(organizerId);
    }

    @DeleteMapping("/participants/{participantId}/emptykidnotes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void emptyKidNotes(@PathVariable Long participantId) {
        organizerService.emptyKidNotes(participantId);
    }

    @PutMapping("/organizers/{organizerId}/photo")
    public void updateOrganizerPhoto(@RequestParam("photo") MultipartFile image, @PathVariable Long organizerId) {
        try {
            organizerService.updateOrganizerPhoto(image, organizerId);
        } catch (IOException e) {
        }
    }

    @PutMapping("/organizers/{organizerId}/update")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateOrganizer(@RequestBody OrganizerPutDTO organizerPutDTO, @PathVariable Long organizerId) {
        Organizer organizerInput = DTOMapper.INSTANCE.convertOrganizerPutDTOtoEntity(organizerPutDTO);
        organizerInput.setId(organizerId);
        organizerService.updateOrganizer(organizerInput);

    }

    @PutMapping("/participants/{participantId}/update")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateParticipant(@RequestBody ParticipantPutDTO participantPutDTO, @PathVariable Long participantId) {
        Participant participantInput = DTOMapper.INSTANCE.convertParticipantPutDTOtoEntity(participantPutDTO);
        participantInput.setId(participantId);
        organizerService.updateParticipant(participantInput);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public OrganizerGetDTO login(@RequestBody OrganizerPostDTO organizerPostDTO) {
        // convert API user to internal representation
        Organizer organizerInput = DTOMapper.INSTANCE.convertOrganizerPostDTOtoEntity(organizerPostDTO);

        // get login user
        Organizer loginOrganizer = organizerService.login(organizerInput);

        // convert internal representation of user back to API
        return DTOMapper.INSTANCE.convertEntityToOrganizerGetDTO(loginOrganizer);
    }

    @PutMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public OrganizerGetDTO logout() {
        try {
            organizerService.logout();
        } catch (Exception e) {
        } finally {
            return DTOMapper.INSTANCE.convertEntityToOrganizerGetDTO(new Organizer());
        }
    }

    @DeleteMapping("/organizers/{organizerId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOrganizer(@PathVariable Long organizerId) {
        organizerService.deleteOrganizer(organizerId);
    }

    @DeleteMapping("/organizers/{organizerId}/participants/{participantId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteParticipant(@PathVariable Long organizerId, @PathVariable Long participantId) {
        organizerService.deleteParticipant(organizerId, participantId);
    }
}
