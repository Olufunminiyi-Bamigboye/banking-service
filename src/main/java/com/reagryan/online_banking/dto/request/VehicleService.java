package com.reagryan.online_banking.dto.request;

public class VehicleService {
    public House convertVehicleToHouse(Vehicle vehicle) {
        House myHouse = new House(
                vehicle.getVehicleBrand(),
                vehicle.getVehicleModel(),
                Integer.parseInt(vehicle.getVehicleYear()),
                vehicle.getVehicleType()
        );
         return myHouse;
    }
}
