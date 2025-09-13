package csci318.demo.cargotracker.bookingms.application.commandservices;

import csci318.demo.cargotracker.bookingms.application.outboundservices.acl.ExternalCargoRoutingService;
import csci318.demo.cargotracker.bookingms.domain.model.aggregates.BookingId;
import csci318.demo.cargotracker.bookingms.domain.model.aggregates.Cargo;
import csci318.demo.cargotracker.bookingms.domain.model.commands.BookCargoCommand;
import csci318.demo.cargotracker.bookingms.domain.model.commands.RouteCargoCommand;
import csci318.demo.cargotracker.bookingms.domain.model.entities.Location;
import csci318.demo.cargotracker.bookingms.domain.model.valueobjects.CargoItinerary;
import csci318.demo.cargotracker.bookingms.domain.model.valueobjects.RouteSpecification;
import csci318.demo.cargotracker.bookingms.infrastructure.repositories.CargoRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Application Service class for the Cargo Booking Commands
 */

@Service
public class CargoBookingCommandService {


    private final CargoRepository cargoRepository;
    private ExternalCargoRoutingService externalCargoRoutingService;

    public CargoBookingCommandService(CargoRepository cargoRepository) {

        this.cargoRepository = cargoRepository;
        this.externalCargoRoutingService = externalCargoRoutingService;
    }

    /**
     * Service Command method to book a new Cargo
     *
     * @return BookingId of the Cargo
     */

    public BookingId bookCargo(BookCargoCommand bookCargoCommand) {

        String random = UUID.randomUUID().toString().toUpperCase();
        String bookIdStr = random.substring(0, random.indexOf("-"));
        System.out.println("Random is :" + bookIdStr);
        bookCargoCommand.setBookingId(bookIdStr);
        Cargo cargo = new Cargo(bookCargoCommand);
        cargoRepository.save(cargo);
        return new BookingId(bookIdStr);
    }

    /**
     * Service Command method to assign a route to a Cargo
     * @param routeCargoCommand
     */

    public void assignRouteToCargo(RouteCargoCommand routeCargoCommand){
        System.out.println("Route Cargo command"+routeCargoCommand.getCargoBookingId());
        Cargo cargo = cargoRepository.findByBookingId(
                new BookingId(routeCargoCommand.getCargoBookingId()));
        CargoItinerary cargoItinerary = externalCargoRoutingService
                .fetchRouteForSpecification(cargo.getRouteSpecification());

        cargo.assignToRoute(cargoItinerary);
        cargoRepository.save(cargo);

    }


}
