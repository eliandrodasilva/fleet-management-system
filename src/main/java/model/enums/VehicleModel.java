package model.enums;

public enum VehicleModel {
    PARADISO_1200(VehicleBrand.MARCOPOLO),
    PARADISO_1800_DD(VehicleBrand.MARCOPOLO),
    TORINO_2014(VehicleBrand.MARCOPOLO),
    MERCEDES_O500(VehicleBrand.MERCEDES_BENZ),
    MERCEDES_O500_RS(VehicleBrand.MERCEDES_BENZ),
    VOLVO_9700(VehicleBrand.VOLVO),
    VOLVO_9900(VehicleBrand.VOLVO),
    LUMINA_COMIL(VehicleBrand.COMIL),
    VOYAGE(VehicleBrand.VOLKSWAGEN);

    private final VehicleBrand vehicleBrand;

    VehicleModel(VehicleBrand vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }

    public VehicleBrand getVehicleBrand() {
        return vehicleBrand;
    }
}
