package csci318.demo.cargotracker.bookingms.interfaces.rest;

import csci318.demo.cargotracker.bookingms.application.commandservices.CargoBookingCommandService;
import csci318.demo.cargotracker.bookingms.application.queryservices.CargoBookingQueryService;
import csci318.demo.cargotracker.bookingms.domain.model.aggregates.BookingId;
import csci318.demo.cargotracker.bookingms.domain.model.aggregates.Cargo;
import csci318.demo.cargotracker.bookingms.interfaces.rest.dto.BookCargoResource;
import csci318.demo.cargotracker.bookingms.interfaces.rest.dto.RouteCargoResource;
import csci318.demo.cargotracker.bookingms.interfaces.rest.transform.BookCargoCommandDTOAssembler;
import csci318.demo.cargotracker.bookingms.interfaces.rest.transform.RouteCargoCommandDTOAssembler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller    // This means that this class is a Controller
@RequestMapping("/cargorouting")
public class CargoRoutingController {

    private CargoBookingCommandService cargoBookingCommandService; // Application Service Dependency


    /**
     * Provide the dependencies
     * @param cargoBookingCommandService
     */
    public CargoRoutingController(CargoBookingCommandService cargoBookingCommandService){
        this.cargoBookingCommandService = cargoBookingCommandService;
    }


    /**
     * POST method to route a cargo
     * @param routeCargoResource
     */
    @PostMapping
    @ResponseBody
    public BookingId routeCargo(@RequestBody RouteCargoResource routeCargoResource){
        cargoBookingCommandService.assignRouteToCargo(
                RouteCargoCommandDTOAssembler
                        .toCommandFromDTO(routeCargoResource));

        BookingId bookingId = new BookingId(routeCargoResource.getBookingId());
        return bookingId;
    }
}
