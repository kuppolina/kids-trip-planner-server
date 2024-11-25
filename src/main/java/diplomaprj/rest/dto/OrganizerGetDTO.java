package diplomaprj.rest.dto;

import java.util.List;

import diplomaprj.entity.Trip;
import diplomaprj.entity.Note;
import diplomaprj.entity.Participant;
import diplomaprj.entity.Task;

public class OrganizerGetDTO {

    private Long id;
    private String name;
    private String username;
    private String token;
    private String image;
    private List<Note> notes;
    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    private List<Task> tasks;
    private String email;
    private String address;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private List<Trip> myTrips;
    private List<Participant> myParticipants;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Trip> getMyTrips() {
        return myTrips;
    }

    public void setMyTrips(List<Trip> myTrips) {
        this.myTrips = myTrips;
    }

    public List<Participant> getMyParticipants() {
        return myParticipants;
    }

    public void setMyParticipants(List<Participant> myParticipants) {
        this.myParticipants = myParticipants;
    }
}
