package csci318.demo.cargotracker.bookingms.interfaces.rest.transform;

import csci318.demo.cargotracker.bookingms.domain.model.commands.RouteCargoCommand;
import csci318.demo.cargotracker.bookingms.interfaces.rest.dto.RouteCargoResource;

/**
 * Assembler class to convert the Book Cargo Resource Data to the Book Cargo Model
 */
public class RouteCargoCommandDTOAssembler {

    /**
     * Static method within the Assembler class
     * @param routeCargoResource
     * @return RouteCargoCommand Model
     */
    public static RouteCargoCommand toCommandFromDTO(RouteCargoResource routeCargoResource){

        return new RouteCargoCommand(routeCargoResource.getBookingId());
    }
}
