package csci318.demo.cargotracker.trackingms.interfaces.events;

import csci318.demo.cargotracker.shareddomain.events.CargoHandledEvent;
import csci318.demo.cargotracker.trackingms.application.AssignTrackingIdCommandService;
import csci318.demo.cargotracker.trackingms.interfaces.events.transform.TrackingActivityCommandEventAssembler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

/**
 * Event Handler for the Cargo Routed Event that the Tracking Bounded Context is interested in
 */
@Configuration
public class CargoHandledEventHandler {

    private static final Logger logger = LoggerFactory.getLogger(CargoHandledEventHandler.class);

    private AssignTrackingIdCommandService assignTrackingIdCommandService; // Application Service Dependency

    /**
     * Provide the dependencies
     * @param assignTrackingIdCommandService
     */
    public CargoHandledEventHandler(AssignTrackingIdCommandService assignTrackingIdCommandService){
        this.assignTrackingIdCommandService = assignTrackingIdCommandService;
    }

    @Bean
    public Consumer<CargoHandledEvent> receiveHandlingEvent() {
        return cargoHandledEvent -> {
            //Process the Event
            System.out.println("XXXXXXXXXX" + cargoHandledEvent.getCargoHandledEventData());
            assignTrackingIdCommandService.addTrackingEvent(
                    TrackingActivityCommandEventAssembler
                            .toCommandFromEvent(cargoHandledEvent));
        };
    }
}