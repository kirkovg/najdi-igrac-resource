package com.najdiigrac.mk.service.impl;

import com.najdiigrac.mk.model.jpa.Location;
import com.najdiigrac.mk.persistence.LocationsRepository;
import com.najdiigrac.mk.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bogda on 19.4.2017.
 */
@Service
public class LocationServiceImpl implements LocationService {
    LocationsRepository locationsRepository;

    @Autowired
    private LocationServiceImpl(LocationsRepository locationsRepository) {
        this.locationsRepository = locationsRepository;
    }

    @Override
    public Location createLocation(String name, String cityName, String streetName, String streetNumber) {
        Location location = new Location();
        location.name = name;
        location.city = cityName;
        location.street = streetName;
        location.streetNumber = streetNumber;
        return locationsRepository.save(location);
    }

    @Override
    public Location updateLocation(Long locationId, String newName, String newCityName, String newStreetName, String newStreetNumber) {
        Location location = locationsRepository.findOne(locationId);
        location.name = newName;
        location.city = newCityName;
        location.street = newStreetName;
        location.streetNumber = newStreetNumber;
        return locationsRepository.save(location);
    }

    @Override
    public void removeLocation(Long locationId) {
        locationsRepository.delete(locationId);
    }

    @Override
    public Location findLocationByName(String name) {
        return locationsRepository.findByName(name);
    }

    @Override
    public List<Location> findAllLocations() {
        return (List)locationsRepository.findAll();
    }
}
