package com.najdiigrac.mk.web;

import com.najdiigrac.mk.model.jpa.Location;
import com.najdiigrac.mk.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Dj Ux on 12-May-17.
 */
@RestController
@RequestMapping(value = "/api/locations", produces = "application/json")
public class LocationController {

    private LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public Location save(@RequestBody Location location) {
        return locationService.createLocation(
                location.name,
                location.city,
                location.street,
                location.streetNumber
        );

    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Location> findAll() {
        return locationService.findAllLocations();
    }

    @RequestMapping(value = "/{locationId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long locationId){
        locationService.removeLocation(locationId);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public Location update(@RequestBody Location location){
        return locationService.updateLocation(
                location.id,
                location.name,
                location.city,
                location.street,
                location.streetNumber
        );
    }
}
