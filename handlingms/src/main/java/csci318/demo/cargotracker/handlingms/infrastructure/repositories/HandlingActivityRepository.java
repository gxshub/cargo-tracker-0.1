package csci318.demo.cargotracker.handlingms.infrastructure.repositories;

import csci318.demo.cargotracker.handlingms.domain.model.aggregates.HandlingActivity;
import csci318.demo.cargotracker.handlingms.domain.model.objectvalues.CargoBookingId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HandlingActivityRepository extends JpaRepository<HandlingActivity,Long> {
    HandlingActivity findByBookingId(CargoBookingId cargoBookingId);
}
