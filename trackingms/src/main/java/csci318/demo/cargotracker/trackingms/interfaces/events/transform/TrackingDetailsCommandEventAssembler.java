package csci318.demo.cargotracker.trackingms.interfaces.events.transform;

import csci318.demo.cargotracker.shareddomain.events.CargoRoutedEvent;
import csci318.demo.cargotracker.trackingms.domail.model.commands.AssignTrackingNumberCommand;

/**
 * Assembler class to convert the Cargo Routed Event to the Assign Tracking Number Command Model
 */
public class TrackingDetailsCommandEventAssembler {

    /**
     * Static method within the Assembler class
     * @param cargoRoutedEvent
     * @return AssignTrackingNumberCommand Model
     */
    public static AssignTrackingNumberCommand toCommandFromEvent(CargoRoutedEvent cargoRoutedEvent){
        return new AssignTrackingNumberCommand(
                cargoRoutedEvent.getCargoRoutedEventData().getBookingId(),"");
    }
}
