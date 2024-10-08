package csci318.demo.cargotracker.bookingms.application.queryservices;


import csci318.demo.cargotracker.bookingms.domain.model.aggregates.BookingId;
import csci318.demo.cargotracker.bookingms.domain.model.aggregates.Cargo;
import csci318.demo.cargotracker.bookingms.infrastructure.repositories.CargoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Application Service which caters to all queries related to the Booking Bounded Context
 */
@Service
public class CargoBookingQueryService {


    private CargoRepository cargoRepository; // Inject Dependencies

    public CargoBookingQueryService(CargoRepository cargoRepository){
        this.cargoRepository = cargoRepository;
    }

    /**
     * Find all Cargos
     * @return List<Cargo>
     */

    public List<Cargo> findAll(){
        return cargoRepository.findAll();
    }

    /**
     * List All Booking Identifiers
     * @return List<BookingId>
     */
   public List<BookingId> findAllBookingIds(){

       return cargoRepository.findAllBookingIds();
   }

    /**
     * Find a specific Cargo based on its Booking Id
     * @param bookingId
     * @return Cargo
     */
    public Cargo find(BookingId bookingId){
        return cargoRepository.findByBookingId(bookingId);
    }
}
