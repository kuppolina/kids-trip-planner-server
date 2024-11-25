package diplomaprj.rest.dto;

import diplomaprj.constant.TripStatus;

public class TripPutDTO {

    private String address;
    private String description;
    private TripStatus status;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
