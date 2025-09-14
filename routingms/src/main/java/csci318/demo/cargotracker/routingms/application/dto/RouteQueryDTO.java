package csci318.demo.cargotracker.routingms.application.dto;

import java.time.LocalDate;

public class RouteQueryDTO {
    private String originUnLocode;
    private String destinationUnLocode;
    private LocalDate deadline;

    public RouteQueryDTO(String originUnLocode, String destinationUnLocode, LocalDate deadline) {
        this.originUnLocode = originUnLocode;
        this.destinationUnLocode = destinationUnLocode;
        this.deadline = deadline;
    }

    public String getOriginUnLocode() {
        return originUnLocode;
    }

    public void setOriginUnLocode(String originUnLocode) {
        this.originUnLocode = originUnLocode;
    }

    public String getDestinationUnLocode() {
        return destinationUnLocode;
    }

    public void setDestinationUnLocode(String destinationUnLocode) {
        this.destinationUnLocode = destinationUnLocode;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
}
