package csci318.demo.cargotracker.routingms.interfaces.rest;

import csci318.demo.cargotracker.routingms.application.CargoRoutingQueryService;
import csci318.demo.cargotracker.routingms.application.dto.RouteQueryDTO;
import csci318.demo.cargotracker.routingms.domain.model.aggregates.Voyage;
import csci318.demo.cargotracker.shareddomain.TransitPath;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller    // This means that this class is a Controller
@RequestMapping("/cargorouting")
public class CargoRoutingController {


    private CargoRoutingQueryService cargoRoutingQueryService; // Application Service Dependency

    /**
     * Provide the dependencies
     * @param cargoRoutingQueryService
     */
    public CargoRoutingController(CargoRoutingQueryService cargoRoutingQueryService){
        this.cargoRoutingQueryService = cargoRoutingQueryService;
    }


    /**
     *
     * @param originUnLocode
     * @param destinationUnLocode
     * @param deadline
     * @return TransitPath - The optimal route for a Route Specification
     */
    @GetMapping(path = "/optimalRoute")
    @ResponseBody
    public TransitPath findOptimalRoute(
            @RequestParam("origin") String originUnLocode,
            @RequestParam("destination") String destinationUnLocode,
            @RequestParam("deadline") String deadline) {
        String decodedString = URLDecoder.decode(deadline, StandardCharsets.UTF_8);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        LocalDate localDate = LocalDate.parse(decodedString, formatter);
        RouteQueryDTO routeQueryDTO = new RouteQueryDTO(originUnLocode, destinationUnLocode, localDate);
        return cargoRoutingQueryService.findOptimalTransitPath(routeQueryDTO);
    }

    /**
     * @return List<Voyage> -  a list of voyages
     */
    @GetMapping(path = "/voyages")
    @ResponseBody
    public List<Voyage> findVoyages(){
        return cargoRoutingQueryService.findAll();
    }
}
