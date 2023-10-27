package com.onetechsol.ipayment.session;

public class UserLocation {


    private String latitude;
    private String longitude;

    public UserLocation(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String latitude() {
        return latitude;
    }

    public String longitude() {
        return longitude;
    }
}
