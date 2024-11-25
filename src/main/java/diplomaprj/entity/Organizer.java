package diplomaprj.entity;

import javax.persistence.*;
import java.io.Serializable;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.List;

@EntityScan
@Entity
@Table(name = "ORGANIZER")
public class Organizer implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name="ID", unique=true, updatable=false, nullable=false)
  @GeneratedValue
  private Long id;

  @Column(name="name", nullable = true, unique = false, updatable = true)
  private String name;

  @Column(name="username", nullable = false, unique = true)
  private String username;

  @Column(name="password", nullable = false, unique = false)
  private String password;

  @Column(name="token", nullable = false, unique = true)
  private String token;

  @Column(name="eventbriteToken", nullable = true)
  private String eventbriteToken;

  @Column(name="email", nullable = true)
  private String email;

  @Column(name="address", nullable = true)
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

  @Transient
  private List <Task> tasks;

  @Transient
  private List <Note> notes;

  public List<Note> getNotes() {
    return notes;
  }

  public void setNotes(List<Note> notes) {
    this.notes = notes;
  }

  @Column(name="image", nullable = true)
  private String image;

  @Transient
  private List<Trip> myTrips;

  @Transient
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    
    public String getImage() {
      return image;
    }
  
    public void setImage(String image) {
      this.image = image;
    }

    public List<Task> getTasks() {
      return tasks;
    }
  
    public void setTasks(List<Task> tasks) {
      this.tasks = tasks;
    }
    public String getEventbriteToken() {
      return eventbriteToken;
    }
  
    public void setEventbriteToken(String eventbriteToken) {
      this.eventbriteToken = eventbriteToken;
    }
}
