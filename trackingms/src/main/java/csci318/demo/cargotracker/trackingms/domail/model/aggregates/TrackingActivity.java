package csci318.demo.cargotracker.trackingms.domail.model.aggregates;


import csci318.demo.cargotracker.trackingms.domail.model.commands.AddTrackingEventCommand;
import csci318.demo.cargotracker.trackingms.domail.model.commands.AssignTrackingNumberCommand;
import csci318.demo.cargotracker.trackingms.domail.model.entities.BookingId;
import csci318.demo.cargotracker.trackingms.domail.model.entities.TrackingActivityEvent;
import csci318.demo.cargotracker.trackingms.domail.model.valueobjects.TrackingEvent;
import csci318.demo.cargotracker.trackingms.domail.model.valueobjects.TrackingEventType;
import csci318.demo.cargotracker.trackingms.domail.model.valueobjects.TrackingLocation;
import csci318.demo.cargotracker.trackingms.domail.model.valueobjects.TrackingVoyageNumber;
import jakarta.persistence.*;
import java.util.Date;
import java.util.UUID;


@Entity
@NamedQueries({
        @NamedQuery(name = "TrackingActivity.findAll",
                query = "Select t from TrackingActivity t"),
        @NamedQuery(name = "TrackingActivity.findByTrackingNumber",
                query = "Select t from TrackingActivity t where t.trackingNumber = :trackingNumber"),
        @NamedQuery(name = "TrackingActivity.findAllTrackingNos",
                query = "Select t.trackingNumber from TrackingActivity t"),
        @NamedQuery(name="TrackingActivity.findByBookingNumber",
                query = "Select t from TrackingActivity t where t.bookingId = :bookingId")})
@Table(name="tracking_activity")
public class TrackingActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private TrackingNumber trackingNumber; // Aggregate Identifier
    @Embedded
    private BookingId bookingId;
    @Embedded
    private TrackingActivityEvent trackingActivityEvent;

    public TrackingActivity(){}

    /**
     * Creates a new Tracking Number
     * @param assignTrackingNumberCommand
     */
    public TrackingActivity(AssignTrackingNumberCommand assignTrackingNumberCommand){
        this.trackingNumber = new TrackingNumber(assignTrackingNumberCommand.getTrackingNumber());
        this.bookingId = new BookingId((assignTrackingNumberCommand.getBookingId()));
        this.trackingActivityEvent = TrackingActivityEvent.EMPTY_ACTIVITY;
    }

    /**
     * Add a tracking event to the Tracking Details
     * @param addTrackingEventCommand
     */
    public void addTrackingEvent(AddTrackingEventCommand addTrackingEventCommand){
        TrackingEvent trackingEvent = new TrackingEvent(
                new TrackingVoyageNumber(addTrackingEventCommand.getVoyageNumber()),
                new TrackingLocation(addTrackingEventCommand.getLocation()),
                new TrackingEventType(addTrackingEventCommand.getEventType(),addTrackingEventCommand.getEventTime()));
        this.trackingActivityEvent.getTrackingEvents().add(trackingEvent);
    }


    /**
     * Gets next Tracking Identifier
     * @return
     */

    public String nextTrackingNumber() {
        String random = UUID.randomUUID().toString().toUpperCase();
        return random.substring(0, random.indexOf("-"));
    }
    public TrackingNumber getTrackingNumber(){
        return this.trackingNumber;
    }

    public BookingId getBookingId(){
        return this.bookingId;
    }

    public TrackingActivityEvent getTrackingActivityEvents() {
        return this.trackingActivityEvent;
    }





}