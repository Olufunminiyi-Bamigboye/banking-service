package com.reagryan.online_banking.dto.request;

public class Vehicle {
    private String vehicleType;
    private String vehicleBrand;
    private String vehicleModel;
    private String vehicleYear;

    public Vehicle(String vehicleType, String vehicleBrand, String vehicleModel, String vehicleYear) {
        this.vehicleType = vehicleType;
        this.vehicleBrand = vehicleBrand;
        this.vehicleModel = vehicleModel;
        this.vehicleYear = vehicleYear;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public String getVehicleYear() {
        return vehicleYear;
    }
}
