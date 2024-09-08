package csci318.demo.cargotracker.trackingms.application;


import csci318.demo.cargotracker.shareddomain.events.CargoBookedEvent;
//import org.springframework.cloud.stream.annotation.EnableBinding;
//import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

/**
 * Event Handler for the Cargo Routed Event that the Tracking Bounded Context is interested in
 */
@Service
public class CargoRoutedEventHandler {

    //@StreamListener(CargoEventSource.BOOKING_INPUT)
    //public void receiveEvent(CargoBookedEvent cargoBookedEvent) {
    //    System.out.println("****READING FROM KAFKA TOPIC cargobookings: "+
    //            cargoBookedEvent.getCargoBookedEventData().getBookingId()+"****");
    //}
    private static final Logger log = LoggerFactory.getLogger(CargoRoutedEventHandler.class);

    @Bean
    public Consumer<CargoBookedEvent> consume(){
        System.out.println("****READING FROM KAFKA TOPIC cargobookings: ");
        //return input -> log.info(input.toString());
        return event -> System.out.println("****READING FROM KAFKA TOPIC cargobookings: "+
                           event.getCargoBookedEventData().getBookingId()+"****");
    }
}
