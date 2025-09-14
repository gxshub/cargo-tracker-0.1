package csci318.demo.cargotracker.handlingms.interfaces;

import csci318.demo.cargotracker.handlingms.application.commandservices.HandlingActivityRegistrationCommandService;
import csci318.demo.cargotracker.handlingms.interfaces.dto.HandlingActivityRegistrationResource;
import csci318.demo.cargotracker.handlingms.interfaces.transform.HandlingActivityRegistrationCommandDTOAssembler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller for the REST API
 */
@Controller    // This means that this class is a Controller
@RequestMapping("/cargohandling")
public  class CargoHandlingController {


    private HandlingActivityRegistrationCommandService handlingActivityRegistrationCommandService; // Application Service Dependency

    /**
     * Provide the dependencies
     * @param handlingActivityRegistrationCommandService
     */
    public CargoHandlingController(HandlingActivityRegistrationCommandService handlingActivityRegistrationCommandService){
        this.handlingActivityRegistrationCommandService = handlingActivityRegistrationCommandService;
    }

    /**
     * POST method to register Handling Activities
     * @param handlingActivityRegistrationResource
     */
    @PostMapping
    @ResponseBody
    public String registerHandlingActivity(@RequestBody HandlingActivityRegistrationResource handlingActivityRegistrationResource){
        System.out.println("***"+handlingActivityRegistrationResource.getBookingId());
        System.out.println("***"+handlingActivityRegistrationResource.getHandlingType());

        handlingActivityRegistrationCommandService.registerHandlingActivityService(HandlingActivityRegistrationCommandDTOAssembler.toCommandFromDTO(handlingActivityRegistrationResource));
        return "Handling Activity Registered";
    }
}