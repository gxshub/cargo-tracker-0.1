package csci318.demo.cargotracker.shareddomain.events;

/**
 * Event Data for the Cargo Booked Event
 */
public class CargoRoutedEventData {

    private String bookingId;

    public CargoRoutedEventData() {
    }

    public CargoRoutedEventData(String bookingId){
        this.bookingId = bookingId;

    }
    public String getBookingId(){return this.bookingId;}

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    @Override
    public String toString() {
        return "CargoRoutedEventData{" +
                "bookingId='" + bookingId + '\'' +
                '}';
    }
}
