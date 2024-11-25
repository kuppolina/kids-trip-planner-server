package diplomaprj.rest.dto;

import java.time.LocalDate;
import java.util.List;

public class ParticipantPutDTO {

    private LocalDate dateOfBirth;

    private String notes;

    private String address;

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
