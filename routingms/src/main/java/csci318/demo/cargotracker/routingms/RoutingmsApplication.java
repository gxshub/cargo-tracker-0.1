package csci318.demo.cargotracker.routingms;

import csci318.demo.cargotracker.routingms.domain.model.aggregates.Voyage;
import csci318.demo.cargotracker.routingms.domain.model.entities.CarrierMovement;
import csci318.demo.cargotracker.routingms.domain.model.valueobjects.Location;
import csci318.demo.cargotracker.routingms.domain.model.valueobjects.Schedule;
import csci318.demo.cargotracker.routingms.domain.model.valueobjects.VoyageNumber;
import csci318.demo.cargotracker.routingms.infrastructure.repositories.VoyageRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class RoutingmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoutingmsApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadDatabase(VoyageRepository voyageRepository) throws Exception {
		return args -> {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Location location1 = new Location("CNHKG");
			Location location2 = new Location("CNHGH");
			Location location3 = new Location("JNTKO");
			Location location4 = new Location("USNYC");
			Date departureDate1 = dateFormat.parse("2019-08-25");
			Date arrivalDate1 = dateFormat.parse("2019-08-28");
			Date departureDate2 = dateFormat.parse("2019-09-01");
			Date arrivalDate2 = dateFormat.parse("2019-09-10");
			Date departureDate3 = dateFormat.parse("2019-09-15");
			Date arrivalDate3 = dateFormat.parse("2019-09-25");
			CarrierMovement carrierMovement1 = new CarrierMovement(location1, location2, departureDate1, arrivalDate1);
			CarrierMovement carrierMovement2 = new CarrierMovement(location2, location3, departureDate2, arrivalDate2);
			CarrierMovement carrierMovement3 = new CarrierMovement(location3, location4, departureDate3, arrivalDate3);
			Voyage voyage1 = new Voyage(new VoyageNumber("0100S"), new Schedule(List.of(carrierMovement1)));
			Voyage voyage2 = new Voyage(new VoyageNumber("0101S"), new Schedule(List.of(carrierMovement2)));
			Voyage voyage3 = new Voyage(new VoyageNumber("0102S"), new Schedule(List.of(carrierMovement3)));
			voyageRepository.save(voyage1);
			voyageRepository.save(voyage2);
			voyageRepository.save(voyage3);
			List<Voyage> voyages = voyageRepository.findAll();
			System.out.println(voyages);
		};
	}

}
