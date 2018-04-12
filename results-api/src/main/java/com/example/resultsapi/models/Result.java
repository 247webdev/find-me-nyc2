package com.example.resultsapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "RESULTS")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("short_title")
    @Column(name = "TITLE")
    private String title;

    @JsonProperty("address")
    @Column(name = "LOCATION")
    private String location;

    @Column(name = "LAT")
    private double lat;

    @Column(name = "LNG")
    private double lng;

    @JsonProperty("section_name")
    @Column(name = "SECTION")
    private String section;

    @JsonProperty("type_of_notice_description")
    @Column(name = "DESCRIPTION")
    private String description;

    @JsonProperty("event_date")
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

    public void lat() {

        final Geocoder geocoder = new Geocoder();
        GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setAddress("Paris, France").setLanguage("en").getGeocoderRequest();
        GeocodeResponse geocoderResponse = geocoder.geocode(geocoderRequest);
        System.out.println(geocoderResponse);
    }

}
