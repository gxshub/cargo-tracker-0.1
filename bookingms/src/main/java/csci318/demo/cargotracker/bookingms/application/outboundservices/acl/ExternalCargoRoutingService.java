package csci318.demo.cargotracker.bookingms.application.outboundservices.acl;

import csci318.demo.cargotracker.bookingms.domain.model.valueobjects.CargoItinerary;
import csci318.demo.cargotracker.bookingms.domain.model.valueobjects.Leg;
import csci318.demo.cargotracker.bookingms.domain.model.valueobjects.RouteSpecification;
import csci318.demo.cargotracker.shareddomain.TransitEdge;
import csci318.demo.cargotracker.shareddomain.TransitPath;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Anti Corruption Service Class
 */

@Service
public class ExternalCargoRoutingService {

    /**
     * The Booking Bounded Context makes an external call to the Routing Service of the Routing Bounded Context to
     * fetch the Optimal Itinerary for a Cargo based on the Route Specification
     * @param routeSpecification
     * @return
     */
    public CargoItinerary fetchRouteForSpecification(RouteSpecification routeSpecification){

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8785/cargorouting/optimalRoute";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("origin",
                        routeSpecification.getOrigin().getUnLocCode())
                .queryParam("destination",
                        routeSpecification.getDestination().getUnLocCode())
                         .queryParam("deadline",
                routeSpecification.getArrivalDeadline().toString());
        // The getForObject method is used to make a GET request to the routing service
        TransitPath transitPath = restTemplate.getForObject(builder.toUriString(), TransitPath.class);
        assert transitPath != null;
        List<Leg> legs = new ArrayList<>(transitPath.getTransitEdges().size());
        for (TransitEdge edge : transitPath.getTransitEdges()) {
            legs.add(toLeg(edge));
        }

        return new CargoItinerary(legs);

    }

    /**
     * Anti-corruption layer conversion method from the routing service's domain model (TransitEdges)
     * to the domain model recognized by the Booking Bounded Context (Legs)
     * @param edge
     * @return
     */
    private Leg toLeg(TransitEdge edge) {
        return new Leg(
                edge.getVoyageNumber(),
                edge.getFromUnLocode(),
                edge.getToUnLocode(),
                edge.getFromDate(),
                edge.getToDate());
        }
}
