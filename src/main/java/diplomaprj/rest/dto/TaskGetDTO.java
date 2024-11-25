package diplomaprj.rest.dto;

public class TaskGetDTO {

    private String content;

    private Long organizerId;

    public Long getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(Long organizerId) {
        this.organizerId = organizerId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
 
}
