package csci318.demo.cargotracker.trackingms.application;

import csci318.demo.cargotracker.trackingms.domail.model.aggregates.TrackingActivity;
import csci318.demo.cargotracker.trackingms.domail.model.aggregates.TrackingNumber;
import csci318.demo.cargotracker.trackingms.domail.model.commands.AddTrackingEventCommand;
import csci318.demo.cargotracker.trackingms.domail.model.commands.AssignTrackingNumberCommand;
import csci318.demo.cargotracker.trackingms.domail.model.entities.BookingId;
import csci318.demo.cargotracker.trackingms.infrastructure.repositories.TrackingRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Application Service class for the Tracking Command Service
 */
@Service
public class AssignTrackingIdCommandService {


    private TrackingRepository trackingRepository;


    public AssignTrackingIdCommandService(TrackingRepository trackingRepository){
        this.trackingRepository = trackingRepository;
    }
    /**
     * Service Command method to assign a tracking id to the booked cargo
     * @return Tracking Number of the Cargo
     */
    @Transactional // Inititate the Transaction
    public TrackingNumber assignTrackingNumberToCargo(AssignTrackingNumberCommand assignTrackingNumberCommand){
        String random = UUID.randomUUID().toString().toUpperCase();
        String trackingNumber = random.substring(0, random.indexOf("-"));
        assignTrackingNumberCommand.setTrackingNumber(trackingNumber);
        TrackingActivity activity = new TrackingActivity(assignTrackingNumberCommand);

        trackingRepository.save(activity); //Store the Tracking Identifier
        return new TrackingNumber(trackingNumber);
    }

    /**
     * Service Command method to assign a route to a Cargo
     * @param addTrackingEventCommand
     */
    @Transactional
    public void addTrackingEvent(AddTrackingEventCommand addTrackingEventCommand){
        TrackingActivity trackingActivity = trackingRepository.findByBookingNumber(
                new BookingId(addTrackingEventCommand.getBookingId()));

        trackingActivity.addTrackingEvent(addTrackingEventCommand);
        trackingRepository.save(trackingActivity);
    }


}