package csci318.demo.cargotracker.trackingms.domain.model.entities;


import csci318.demo.cargotracker.trackingms.domain.model.valueobjects.TrackingEvent;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Embeddable
public class TrackingActivityEvent {


    public static final TrackingActivityEvent EMPTY_ACTIVITY = new TrackingActivityEvent();
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "tracking_id")
    private List<TrackingEvent> trackingEvents = new ArrayList<TrackingEvent>();

    public TrackingActivityEvent() {
        // Nothing to initialize.
    }

    public TrackingActivityEvent(List<TrackingEvent> trackingEvents) {
        this.trackingEvents = trackingEvents;
    }

    public List<TrackingEvent> getTrackingEvents() {
        return trackingEvents;
    }

}