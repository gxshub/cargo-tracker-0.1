package csci318.demo.cargotracker.bookingms.domain.model.aggregates;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

/**
 * Aggregate Identifier for the Cargo Aggregate
 */
@Embeddable
public class BookingId implements Serializable {

    @Column(name="booking_id")
    private String bookingId;

    public BookingId(){}

    public BookingId(String bookingId){this.bookingId = bookingId;}

    public String getBookingId(){return this.bookingId;}
}
