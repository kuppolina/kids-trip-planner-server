package diplomaprj.entity;

import javax.persistence.*;
import java.io.Serializable;
import diplomaprj.constant.TripStatus;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Entity
@EntityScan
@Table(name = "TRIP")
public class Trip implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", unique = true, updatable = false, nullable = false)
    @GeneratedValue
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "status", nullable = false)
    private TripStatus status;

    @Column(name = "address", nullable = true)
    private String address;

    @Column(name = "organizerId", nullable = false)
    private Long organizerId;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "image", nullable = true)
    private String image;

    @Transient
    private Organizer organizer;

    @Transient
    private List<Participant> participants;

    @Column(name = "numberOfKids", nullable = true)
    private String numberOfKids; 

    public String getNumberOfKids() {
        return numberOfKids;
    }

    public void setNumberOfKids(String numberOfKids) {
        this.numberOfKids = numberOfKids;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TripStatus getStatus() {
        return status;
    }

    public void setStatus(TripStatus status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(Long organizerId) {
        this.organizerId = organizerId;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}