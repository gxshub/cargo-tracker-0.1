package csci318.demo.cargotracker.routingms.application;

import csci318.demo.cargotracker.routingms.application.dto.RouteQueryDTO;
import csci318.demo.cargotracker.routingms.domain.model.aggregates.Voyage;
import csci318.demo.cargotracker.routingms.domain.model.entities.CarrierMovement;
import csci318.demo.cargotracker.routingms.infrastructure.repositories.VoyageRepository;
import csci318.demo.cargotracker.shareddomain.TransitEdge;
import csci318.demo.cargotracker.shareddomain.TransitPath;
import csci318.demo.cargotracker.routingms.domain.model.exceptions.NonTransitPathFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Application Service class for the Cargo Routing Query service
 */
@Service
public class CargoRoutingQueryService {

    private VoyageRepository voyageRepository; // Inject Dependencies

    public CargoRoutingQueryService(VoyageRepository voyageRepository){
        this.voyageRepository = voyageRepository;
    }
    /**
     * Returns all Voyages
     * @return
     */
    @Transactional
    public List<Voyage> findAll(){
        return voyageRepository.findAll();
    }


    /**
     * Returns all an optimal transition path
     * @return
     */
    @Transactional
    public TransitPath findOptimalTransitPath(RouteQueryDTO routeQueryDTO) {
        /* TODO it should return an optimal transition path
         */
        if (!Objects.equals(routeQueryDTO.getOriginUnLocode(), "CNHKG")
                || !Objects.equals(routeQueryDTO.getDestinationUnLocode(), "USNYC")) {
            throw new NonTransitPathFoundException("No transit path found for the given origin and destination");
        }
        List<Voyage> voyages = voyageRepository.findAll();
        TransitPath transitPath = new TransitPath();
        List<TransitEdge> transitEdges = new ArrayList<>();
        for(Voyage voyage:voyages){
            TransitEdge transitEdge = new TransitEdge();
            transitEdge.setVoyageNumber(voyage.getVoyageNumber().getVoyageNumber());
            CarrierMovement movement =
                    ((List<CarrierMovement>)voyage.getSchedule().getCarrierMovements()).get(0);
            transitEdge.setFromDate(movement.getArrivalDate());
            transitEdge.setToDate(movement.getDepartureDate());
            transitEdge.setFromUnLocode(movement.getArrivalLocation().getUnLocCode());
            transitEdge.setToUnLocode(movement.getDepartureLocation().getUnLocCode());
            transitEdges.add(transitEdge);

        }
        transitPath.setTransitEdges(transitEdges);
        System.out.println(transitPath);
        return transitPath;
    }
}
