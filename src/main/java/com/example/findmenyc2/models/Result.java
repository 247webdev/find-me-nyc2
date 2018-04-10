package com.example.findmenyc2.models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "LOCATION")
    private String location;

    @Column(name = "LAT")
    private double lat;

    @Column(name = "LNG")
    private double lng;

    @Column(name = "SECTION")
    private String section;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "DATE_AND_TIME")
    private String dateAndTime;

    public Result(String title, String location, double lat, double lng, String section, String description, String dateAndTime) {
        this.title = title;
        this.location = location;
        this.lat = lat;
        this.lng = lng;
        this.section = section;
        this.description = description;
        this.dateAndTime = dateAndTime;
    }
}
