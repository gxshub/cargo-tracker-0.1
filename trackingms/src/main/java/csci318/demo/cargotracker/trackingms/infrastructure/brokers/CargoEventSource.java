package csci318.demo.cargotracker.trackingms.infrastructure.brokers;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * Interface depicting all input channels
 */
public interface CargoEventSource {

    String BOOKING_INPUT = "cargoBookingChannel";
    String ROUTING_INPUT = "cargoRoutingChannel";

    @Input
    SubscribableChannel cargoBookingChannel();

    @Input
    SubscribableChannel cargoRoutingChannel();

}
