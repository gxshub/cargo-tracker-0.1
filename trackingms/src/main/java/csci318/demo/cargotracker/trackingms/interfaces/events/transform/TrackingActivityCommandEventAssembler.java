package csci318.demo.cargotracker.trackingms.interfaces.events.transform;

import csci318.demo.cargotracker.shareddomain.events.CargoHandledEvent;
import csci318.demo.cargotracker.shareddomain.events.CargoHandledEventData;
import csci318.demo.cargotracker.trackingms.domail.model.commands.AddTrackingEventCommand;

/**
 * Assembler class to convert the Cargo Routed Event to the Assign Tracking Number Command Model
 */
public class TrackingActivityCommandEventAssembler {

    /**
     * Static method within the Assembler class
     * @param cargoHandledEvent
     * @return AssignTrackingNumberCommand Model
     */
    public static AddTrackingEventCommand toCommandFromEvent(CargoHandledEvent cargoHandledEvent){
        CargoHandledEventData eventData = cargoHandledEvent.getCargoHandledEventData();
        return new AddTrackingEventCommand(
                eventData.getBookingId(),
                eventData.getHandlingCompletionTime(),
                eventData.getHandlingType(),
                eventData.getHandlingLocation(),
                eventData.getVoyageNumber());
    }
}
