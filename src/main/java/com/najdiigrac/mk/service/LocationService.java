package com.najdiigrac.mk.service;

import com.najdiigrac.mk.model.jpa.Location;

import java.util.List;

/**
 * Created by bogda on 19.4.2017.
 */
public interface LocationService {
    Location createLocation(
            String name,
            String cityName,
            String streetName,
            String streetNumber
    );

    Location updateLocation(
            Long newLocationId,
            String newName,
            String newCityName,
            String newStreetName,
            String newStreetNumber
    );

    void removeLocation(Long locationId);

    Location findLocationByName(String name);

    List<Location> findAllLocations();
}
