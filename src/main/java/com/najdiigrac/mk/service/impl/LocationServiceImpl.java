package com.najdiigrac.mk.service.impl;

import com.najdiigrac.mk.model.jpa.Location;
import com.najdiigrac.mk.persistence.LocationsRepository;
import com.najdiigrac.mk.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by bogda on 19.4.2017.
 */
@Service
public class LocationServiceImpl implements LocationService {
    LocationsRepository locationsRepository;

    @Autowired
    private LocationServiceImpl(LocationsRepository locationsRepository){
        this.locationsRepository = locationsRepository;
    }

    @Override
    public Location createLocation(String name,String cityName,String streetName,String streetNumber) {
        Location location = new Location();
        location.name = name;
        location.city = cityName;
        location.street = streetName;
        location.streetNumber = streetNumber;
        return locationsRepository.save(location);
    }
}
