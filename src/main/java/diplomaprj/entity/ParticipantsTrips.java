package diplomaprj.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PARTICIPANTTRIPS")
public class ParticipantsTrips implements Serializable {
  
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", unique = true, updatable = false, nullable = false)
    @GeneratedValue
    private Long id;  

    @Column(name = "participantId", nullable = false)
    private Long participantId;  

    @Column(name = "tripId", nullable = false)
    private Long tripId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Long participantId) {
        this.participantId = participantId;
    }

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    } 
    
    public ParticipantsTrips() {}

    public ParticipantsTrips(Long participantId, Long tripId) {
        this.participantId = participantId;
        this.tripId = tripId;
    }
}
