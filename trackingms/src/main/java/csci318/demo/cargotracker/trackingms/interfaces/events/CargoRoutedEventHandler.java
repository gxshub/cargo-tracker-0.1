package csci318.demo.cargotracker.trackingms.interfaces.events;

import csci318.demo.cargotracker.shareddomain.events.CargoRoutedEvent;
import csci318.demo.cargotracker.trackingms.application.AssignTrackingIdCommandService;
import csci318.demo.cargotracker.trackingms.interfaces.events.transform.TrackingDetailsCommandEventAssembler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

/**
 * Event Handler for the Cargo Routed Event that the Tracking Bounded Context is interested in
 */
@Configuration
public class CargoRoutedEventHandler {

    private AssignTrackingIdCommandService assignTrackingIdCommandService; // Application Service Dependency

    /**
     * Provide the dependencies
     * @param assignTrackingIdCommandService
     */
    public CargoRoutedEventHandler(AssignTrackingIdCommandService assignTrackingIdCommandService){
        this.assignTrackingIdCommandService = assignTrackingIdCommandService;
    }

    @Bean
    public Consumer<CargoRoutedEvent> receiveRoutingEvent() {
        return cargoRoutedEvent -> {
            System.out.println("Cargo routed Event" + cargoRoutedEvent);
            System.out.println(cargoRoutedEvent.getCargoRoutedEventData());
            System.out.println(cargoRoutedEvent.getCargoRoutedEventData().getBookingId());
            //Process the Event
            assignTrackingIdCommandService.assignTrackingNumberToCargo(
                    TrackingDetailsCommandEventAssembler.toCommandFromEvent(cargoRoutedEvent));
        };
    }
}
