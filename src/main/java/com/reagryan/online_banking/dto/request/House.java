package com.reagryan.online_banking.dto.request;

public class House {
    private String houseName;
    private String houseAddress;
    private int houseNumber;
    private String houseType;

    public House(String houseName, String houseAddress, int houseNumber, String houseType) {
        this.houseName = houseName;
        this.houseAddress = houseAddress;
        this.houseNumber = houseNumber;
        this.houseType = houseType;
    }

    public String getHouseName() {
        return houseName;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public String getHouseType() {
        return houseType;
    }
}
