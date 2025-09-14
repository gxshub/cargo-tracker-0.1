package csci318.demo.cargotracker.handlingms.interfaces.transform;

import csci318.demo.cargotracker.handlingms.domain.model.commands.HandlingActivityRegistrationCommand;
import csci318.demo.cargotracker.handlingms.interfaces.dto.HandlingActivityRegistrationResource;

/**
 * Assembler class to convert the Book Cargo Resource Data to the Book Cargo Model
 */
public class HandlingActivityRegistrationCommandDTOAssembler {

    /**
     * Static method within the Assembler class
     * @param handlingActivityRegistrationResource
     * @return BookCargoCommand Model
     */
    public static HandlingActivityRegistrationCommand toCommandFromDTO(HandlingActivityRegistrationResource handlingActivityRegistrationResource){

        System.out.println("Booking Id "+handlingActivityRegistrationResource.getBookingId());
        System.out.println("Voyage Number"+handlingActivityRegistrationResource.getVoyageNumber());
        System.out.println("Unlocode"+handlingActivityRegistrationResource.getUnLocode());
        System.out.println("HandlingType"+handlingActivityRegistrationResource.getHandlingType());
        System.out.println("Completion Time"+handlingActivityRegistrationResource.getCompletionTime());
        return new HandlingActivityRegistrationCommand(
                handlingActivityRegistrationResource.getBookingId(),
                handlingActivityRegistrationResource.getVoyageNumber(),
                handlingActivityRegistrationResource.getUnLocode(),
                handlingActivityRegistrationResource.getHandlingType(),
                java.sql.Date.valueOf(handlingActivityRegistrationResource.getCompletionTime())
        );
    }
}
