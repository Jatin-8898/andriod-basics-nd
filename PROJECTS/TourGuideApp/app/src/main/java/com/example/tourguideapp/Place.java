package com.example.tourguideapp;

public class Place {
    private static final int DEFAULT_ID = -1;
    private String placeName;
    private String placeDesc;
    private String placeLocation;
    private String placeNumber;
    private int placeImg = DEFAULT_ID;

    public Place(String placeName, String placeDesc, String placeLocation, String placeNumber, int placeImg) {
        this.placeName = placeName;
        this.placeDesc = placeDesc;
        this.placeLocation = placeLocation;
        this.placeNumber = placeNumber;
        this.placeImg = placeImg;
    }

    public Place(String placeName, String placeDesc, String placeLocation, String placeNumber) {
        this.placeName = placeName;
        this.placeDesc = placeDesc;
        this.placeLocation = placeLocation;
        this.placeNumber = placeNumber;
    }

    public Place(String placeName, String placeLocation, int placeImg) {
        this.placeName = placeName;
        this.placeLocation = placeLocation;
        this.placeImg = placeImg;
    }

    public Place(String placeName, String placeDesc) {
        this.placeName = placeName;
        this.placeDesc = placeDesc;
    }

    public String getPlaceName() {
        return placeName;
    }

    public String getPlaceDesc() {
        return placeDesc;
    }

    public String getPlaceLocation() {
        return placeLocation;
    }

    public String getPlaceNumber() {
        return placeNumber;
    }

    public int getPlaceImg() {
        return placeImg;
    }

    public boolean hasImage() {
        return placeImg != DEFAULT_ID;
    }
}
