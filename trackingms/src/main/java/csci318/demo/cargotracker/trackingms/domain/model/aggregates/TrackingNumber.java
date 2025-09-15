package csci318.demo.cargotracker.trackingms.domain.model.aggregates;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class TrackingNumber {

    @Column(name="tracking_number")
    private String trackingNumber;

    public TrackingNumber(){}

    public TrackingNumber(String trackingNumber){this.trackingNumber = trackingNumber;}

    public String getTrackingNumber(){return this.trackingNumber;}
}
