package csci318.demo.cargotracker.bookingms.domain.model.commands;

import csci318.demo.cargotracker.bookingms.domain.model.valueobjects.CargoItinerary;

import java.util.Date;

/**
 * Command Class to assign a route to a booked cargo
 */
public class RouteCargoCommand {
    private String cargoBookingId;

    public RouteCargoCommand(){ }

    public RouteCargoCommand(String cargoBookingId){
        this.setCargoBookingId(cargoBookingId);
    }


    public String getCargoBookingId() {
        return cargoBookingId;
    }

    public void setCargoBookingId(String cargoBookingId) {
        this.cargoBookingId = cargoBookingId;
    }


}
