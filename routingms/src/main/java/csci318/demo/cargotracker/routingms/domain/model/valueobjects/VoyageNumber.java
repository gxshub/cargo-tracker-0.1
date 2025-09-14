package csci318.demo.cargotracker.routingms.domain.model.valueobjects;

import jakarta.persistence.*;
@Embeddable
public class VoyageNumber {
    @Column(name="voyage_number")
    private String voyageNumber;
    public VoyageNumber(){}
    public VoyageNumber(String voyageNumber){this.voyageNumber = voyageNumber;}
    public String getVoyageNumber(){return this.voyageNumber;}
    public void setVoyageNumber(String voyageNumber){this.voyageNumber = voyageNumber;}


    @Override
    public String toString() {
        return "VoyageNumber{" +
                "voyageNumber='" + voyageNumber + '\'' +
                '}';
    }
}
