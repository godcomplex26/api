package com.malmap.api.model;

import lombok.Getter;

@Getter
public class IpLocation {
    private String countryCode;
    private Double latitude;
    private Double longitude;

    public IpLocation(String countryCode, Double latitude, Double longitude) {
        this.countryCode = countryCode;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getters and setters
}
