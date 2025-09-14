package csci318.demo.cargotracker.shareddomain.events;

public class CargoHandledEvent {
    private CargoHandledEventData cargoHandledEventData;
    public CargoHandledEvent(){}
    public CargoHandledEvent(CargoHandledEventData cargoHandledEventData){
        this.cargoHandledEventData = cargoHandledEventData;
    }
    public CargoHandledEventData getCargoHandledEventData() {
        return this.cargoHandledEventData;
    }

    public void setCargoHandledEventData(CargoHandledEventData cargoHandledEventData) {
        this.cargoHandledEventData = cargoHandledEventData;
    }

    @Override
    public String toString() {
        return "CargoHandledEvent{" +
                "cargoHandledEventData=" + cargoHandledEventData +
                '}';
    }
}