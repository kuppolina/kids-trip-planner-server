package diplomaprj.rest.dto;

import java.time.LocalDate;
import java.util.List;

import diplomaprj.entity.KidNote;
import diplomaprj.entity.Parent;

public class ParticipantGetDTO {

    private Long id;
    
    private String name;

    private LocalDate dateOfBirth;

    private String address;

    private String notes;

    private Parent parent;

    private List<KidNote> kidNotes;

    private String image;

    private int age;

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<KidNote> getKidNotes() {
        return kidNotes;
    }

    public void setKidNotes(List<KidNote> kidNotes) {
        this.kidNotes = kidNotes;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
