package csci318.demo.cargotracker.handlingms.domain.model.objectvalues;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class CargoBookingId {
    @Column(name = "booking_id")
    private String bookingId;
    public CargoBookingId(){}
    public CargoBookingId(String bookingId){this.bookingId = bookingId;}
    public void setBookingId(String bookingId){this.bookingId = bookingId;}
    public String getBookingId(){return this.bookingId;}
}
