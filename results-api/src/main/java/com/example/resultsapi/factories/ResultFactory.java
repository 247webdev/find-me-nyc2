package com.example.resultsapi.factories;

import com.example.resultsapi.models.Result;
import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import org.springframework.stereotype.Component;

@Component
public class ResultFactory {

    public Result addLatitudeAndLongitudeToResult(Result rawResult) {
        final Geocoder geocoder = new Geocoder();
        GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setAddress(rawResult.getLocation()).setLanguage("en").getGeocoderRequest();
        GeocodeResponse geocoderResponse = geocoder.geocode(geocoderRequest);

        double lat = geocoderResponse.getResults().get(0).getGeometry().getLocation().getLat().doubleValue();
        double lng = geocoderResponse.getResults().get(0).getGeometry().getLocation().getLng().doubleValue();

        rawResult.setLat(lat);
        rawResult.setLng(lng);

        return rawResult;
    }

}
