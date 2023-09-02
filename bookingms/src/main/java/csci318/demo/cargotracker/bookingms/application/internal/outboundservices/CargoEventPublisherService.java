package csci318.demo.cargotracker.bookingms.application.internal.outboundservices;

import csci318.demo.cargotracker.bookingms.infrastructure.brokers.CargoEventSource;
import csci318.demo.cargotracker.shareddomain.events.CargoBookedEvent;
import csci318.demo.cargotracker.shareddomain.events.CargoRoutedEvent;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 *
 */
@Service
@EnableBinding(CargoEventSource.class)
public class CargoEventPublisherService {

    CargoEventSource cargoEventSource;

    public CargoEventPublisherService(CargoEventSource cargoEventSource){
        this.cargoEventSource = cargoEventSource;
    }

    @TransactionalEventListener
    public void handleCargoBookedEvent(CargoBookedEvent cargoBookedEvent){
        cargoEventSource.cargoBooking().send(MessageBuilder.withPayload(cargoBookedEvent).build());
    }

    @TransactionalEventListener
    public void handleCargoRoutedEvent(CargoRoutedEvent cargoRoutedEvent){
        cargoEventSource.cargoRouting().send(MessageBuilder.withPayload(cargoRoutedEvent).build());
    }
}
