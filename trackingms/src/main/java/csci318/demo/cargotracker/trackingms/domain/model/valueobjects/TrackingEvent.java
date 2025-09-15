package csci318.demo.cargotracker.trackingms.domain.model.valueobjects;

import jakarta.persistence.*;

/**
 * Tracking Event Details
 */
@Entity
@Table(name="tracking_handling_events")
public class TrackingEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private TrackingVoyageNumber trackingVoyageNumber;
    @Embedded
    private TrackingLocation trackingLocation;
    @Embedded
    private TrackingEventType trackingEventType;

    public TrackingEvent(){}

    public TrackingEvent(
            TrackingVoyageNumber trackingVoyageNumber,
            TrackingLocation trackingLocation,
            TrackingEventType trackingEventType){
        this.trackingEventType = trackingEventType;
        this.trackingVoyageNumber = trackingVoyageNumber;
        this.trackingLocation = trackingLocation;
    }


    public TrackingVoyageNumber getTrackingVoyageNumber(){return this.trackingVoyageNumber;}
    public TrackingLocation getTrackingLocation(){return this.trackingLocation;}
    public TrackingEventType getTrackingEventType(){return this.trackingEventType;}

}