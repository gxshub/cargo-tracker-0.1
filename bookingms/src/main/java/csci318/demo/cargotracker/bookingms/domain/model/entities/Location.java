package csci318.demo.cargotracker.bookingms.domain.model.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Location class represented by a unique 5-diigit UN Location code.
 */
@Embeddable
public class Location {

    @Column(name = "origin_id", insertable = false, updatable = false)
    private String unLocCode;
    public Location(){}
    public Location(String unLocCode){this.unLocCode = unLocCode;}
    public void setUnLocCode(String unLocCode){this.unLocCode = unLocCode;}
    public String getUnLocCode(){return this.unLocCode;}
}
