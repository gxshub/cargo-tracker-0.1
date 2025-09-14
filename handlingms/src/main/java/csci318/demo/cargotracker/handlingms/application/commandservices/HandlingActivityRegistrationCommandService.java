package csci318.demo.cargotracker.handlingms.application.commandservices;

import csci318.demo.cargotracker.handlingms.domain.model.aggregates.HandlingActivity;
import csci318.demo.cargotracker.handlingms.domain.model.commands.HandlingActivityRegistrationCommand;
import csci318.demo.cargotracker.handlingms.domain.model.objectvalues.CargoBookingId;
import csci318.demo.cargotracker.handlingms.domain.model.objectvalues.Location;
import csci318.demo.cargotracker.handlingms.domain.model.objectvalues.Type;
import csci318.demo.cargotracker.handlingms.domain.model.objectvalues.VoyageNumber;
import csci318.demo.cargotracker.handlingms.infrastructure.repositories.HandlingActivityRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class HandlingActivityRegistrationCommandService {


    private HandlingActivityRepository handlingActivityRepository;

    public HandlingActivityRegistrationCommandService(HandlingActivityRepository handlingActivityRepository){
        this.handlingActivityRepository = handlingActivityRepository;
    }

    /**
     * Service Command method to register a new Handling Activity
     * @return BookingId of the CargoBookingId
     */
    @Transactional
    public void registerHandlingActivityService(HandlingActivityRegistrationCommand handlingActivityRegistrationCommand){
        System.out.println("Handling Voyage Number is"+handlingActivityRegistrationCommand.getVoyageNumber());
        if(!handlingActivityRegistrationCommand.getVoyageNumber().equals("")) {
            HandlingActivity handlingActivity = new HandlingActivity(
                    new CargoBookingId(handlingActivityRegistrationCommand.getBookingId()),
                    handlingActivityRegistrationCommand.getCompletionTime(),
                    Type.valueOf(handlingActivityRegistrationCommand.getHandlingType()),
                    new Location(handlingActivityRegistrationCommand.getUnLocode()),
                    new VoyageNumber(handlingActivityRegistrationCommand.getVoyageNumber()));
            handlingActivityRepository.save(handlingActivity);

        }else{
            HandlingActivity handlingActivity = new HandlingActivity(
                    new CargoBookingId(handlingActivityRegistrationCommand.getBookingId()),
                    handlingActivityRegistrationCommand.getCompletionTime(),
                    Type.valueOf(handlingActivityRegistrationCommand.getHandlingType()),
                    new Location(handlingActivityRegistrationCommand.getUnLocode()));
            handlingActivityRepository.save(handlingActivity);
        }




    }
}