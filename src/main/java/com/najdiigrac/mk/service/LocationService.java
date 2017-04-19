package com.najdiigrac.mk.service;

import com.najdiigrac.mk.model.jpa.Location;

/**
 * Created by bogda on 19.4.2017.
 */
public interface LocationService {
    Location createLocation(String name,String cityName,String streetName,String streetNumber);
}
