package csci318.demo.cargotracker.handlingms.application.outboundservices;

import csci318.demo.cargotracker.shareddomain.events.CargoHandledEvent;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class CargoEventPublisherService {

    private final StreamBridge streamBridge;

    public CargoEventPublisherService(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @TransactionalEventListener
    public void handleCargoHandledEvent(CargoHandledEvent cargoHandledEvent) {
        streamBridge.send("cargoHandlingChannel", cargoHandledEvent);
    }

}